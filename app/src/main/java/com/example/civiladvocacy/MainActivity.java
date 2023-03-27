package com.example.civiladvocacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerViewOfficialList;
    private OfficialAdapter officialAdapter;
    private ArrayList<Official> officialList = new ArrayList<>();
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int LOCATION_REQUEST = 111;

    private static String locationString = "Unspecified Location";
    private TextView locationTextView;
    private boolean networkFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationTextView = findViewById(R.id.location_main_vid);
        recyclerViewOfficialList = findViewById(R.id.recycleview_main_vid);

        officialAdapter = new OfficialAdapter(officialList, this);
        recyclerViewOfficialList.setAdapter(officialAdapter);
        recyclerViewOfficialList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        networkFlag = hasNetworkConnection();
        if(networkFlag){
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            getOfficialData();
            determineCurrentLocation();
        }
        else{
            locationTextView.setText("No Internet");
        }


    }

    public void getOfficialData(){
        for(int i =0;i<4;i++){
            Official official = new Official();
            official.setOfficial_email_id("jinitemail");
            official.setOfficial_name("jinit");
            official.setOfficial_office("seceratry");
            officialList.add(official);
        }
        officialAdapter.notifyItemRangeChanged(0,officialList.size());

    }

    @Override
    public void onClick(View view) {
        // get item position to be edited
        int position = recyclerViewOfficialList.getChildLayoutPosition(view);
        //remove from recycler view's list
        Official official = officialList.get(position);
        Toast.makeText(this, "clicked: " + position, Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this, EditActivity.class);
        // setting position in global variable to know which note to update after edit activity
        //intent.putExtra("EditNote", note);
        //position_for_editNote = position;
        //flag_editNote = true;
        //intent.putExtra("Position",position);
        //activityResultLauncher.launch(intent);
        //return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private boolean hasNetworkConnection() {
        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selected = item.getItemId();
        if(selected == R.id.about_menu_vid){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            //builder.setIcon(R.drawable.icon1);

            builder.setMessage("Here is useful info.");
            builder.setTitle("About Dialog");

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(selected == R.id.location_menu_vid){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setIcon(R.drawable.location);

            builder.setMessage("Here is useful info.");
            builder.setTitle("Location Dialog");

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void determineCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    // Got last known location. In some situations this can be null.
                    if (location != null) {
                        locationString = getAddress(location);
                        locationTextView.setText(locationString);
                    }
                })
                .addOnFailureListener(this, e ->
                        Toast.makeText(MainActivity.this,
                                e.getMessage(), Toast.LENGTH_LONG).show());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_REQUEST) {
            if (permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    determineCurrentLocation();
                } else {
                    locationTextView.setText(R.string.deniedText);
                }
            }
        }
    }

    private String getAddress(Location location) {

        StringBuilder stringBuilder = new StringBuilder();

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String plotNo = addresses.get(0).getSubThoroughfare();
            String streetName = addresses.get(0).getThoroughfare();
            String postalCode = addresses.get(0).getPostalCode();
            stringBuilder.append(String.format(Locale.getDefault(), "%s %s,%s,%s %s", plotNo,streetName,postalCode,city, state));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}