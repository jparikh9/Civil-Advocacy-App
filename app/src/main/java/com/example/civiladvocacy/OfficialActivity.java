package com.example.civiladvocacy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import android.content.pm.PackageManager;

import org.w3c.dom.Text;

public class OfficialActivity extends AppCompatActivity {

    String email = "";
    private String phoneNo;
    private String website;
    private String address;
    TextView textView;
    TextView textViewEmailTitle;
    TextView textViewPhoneNo;
    TextView textViewPhoneTitle;
    TextView textViewAddress;
    TextView textViewWebsite;
    TextView textViewName;
    ImageView imageViewPhoto;
    TextView textViewAddressTitle;
    TextView textViewWebsiteTitle;
    ImageView facebook;
    ImageView twitter;
    ImageView youtube;
    TextView location;
    TextView office;
    TextView party;
    String fb;
    String tw;
    String yt;
    ConstraintLayout ct;
    ImageView partylogo;
    String partystr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official);
        ct = findViewById(R.id.constraint_layout_officialactivity);
        textViewName = findViewById(R.id.name_officialactivity_vid);
        textView = findViewById(R.id.emailid_officialactivity_id);
        textViewPhoneNo = findViewById(R.id.phoneNo_officialactivity_vid);
        textViewWebsite = findViewById(R.id.url_officialactivity_vid);
        textViewAddress = findViewById(R.id.addressvalue_officialactivity_vid);
        textViewPhoneTitle = findViewById(R.id.phone_officialactivity_vid);
        imageViewPhoto = findViewById(R.id.photo_officialactivity_vid);
        textViewAddressTitle = findViewById(R.id.addresstitle_officialactivity_vid);
        textViewWebsiteTitle = findViewById(R.id.website_officialactivity_vid);
        textViewEmailTitle = findViewById(R.id.email_officialactivity_vid);
        facebook = findViewById(R.id.facebook_officialactivity_vid);
        twitter = findViewById(R.id.twitter_officialactivity_vid);
        youtube = findViewById(R.id.youtube_officialactivity_vid);
        location = findViewById(R.id.location_officialactivity_vid);
        office = findViewById(R.id.office_officialactivity_vid);
        party = findViewById(R.id.party_officialactivity_vid);
        partylogo = findViewById(R.id.partylogo_officialactivity_vid);
        Intent intent = getIntent();
        if(intent.hasExtra("location")){
            location.setText((String) getIntent().getSerializableExtra("location"));
        }
        if(intent.hasExtra("twitter")){
            tw = (String)getIntent().getSerializableExtra("twitter");
        }
        else{
            twitter.setVisibility(View.GONE);
        }
        if(intent.hasExtra("facebook")){
            fb = (String)getIntent().getSerializableExtra("facebook");
        }
        else{
            facebook.setVisibility(View.GONE);
        }
        if(intent.hasExtra("youtube")){
            yt = (String)getIntent().getSerializableExtra("youtube");
        }
        else{
            youtube.setVisibility(View.GONE);
        }
        if(intent.hasExtra("Email")){
            email = (String) getIntent().getSerializableExtra("Email");
            textView.setText(email);
        }
        else{
            textView.setVisibility(View.GONE);
            textViewEmailTitle.setVisibility(View.GONE);
        }
        if(intent.hasExtra("phoneNo")){
            phoneNo = (String) getIntent().getSerializableExtra("phoneNo");
            textViewPhoneNo.setText(phoneNo);
        }
        else{
            textViewPhoneNo.setVisibility(View.GONE);
            textViewPhoneTitle.setVisibility(View.GONE);
        }
        if(intent.hasExtra("address")){
            address = (String) getIntent().getSerializableExtra("address");
            textViewAddress.setText(address);
        }
        else{
            textViewAddress.setVisibility(View.GONE);
            textViewAddressTitle.setVisibility(View.GONE);
        }
        if(intent.hasExtra("website")){
            website = (String) getIntent().getSerializableExtra("website");
            textViewWebsite.setText(website);
        }
        else{
            textViewWebsite.setVisibility(View.GONE);
            textViewWebsiteTitle.setVisibility(View.GONE);
        }
        if(intent.hasExtra("official_name")){
            textViewName.setText((String)getIntent().getSerializableExtra("official_name"));
        }
        if(intent.hasExtra("official_office")){
            office.setText((String)getIntent().getSerializableExtra("official_office"));
        }
        if(intent.hasExtra("party")){
            party.setText((String)getIntent().getSerializableExtra("party"));
            partystr = (String)getIntent().getSerializableExtra("party");
            if(partystr.equalsIgnoreCase("Democratic Party") || partystr.equalsIgnoreCase("Democrat party")){
                ct.setBackgroundColor(Color.BLUE);
                partylogo.setImageResource(R.drawable.dem_logo);
            } else if (partystr.equalsIgnoreCase("Republican Party")) {
                ct.setBackgroundColor(Color.RED);
                imageViewPhoto.setImageResource(R.drawable.rep_logo);
            }
            else{
                ct.setBackgroundColor(Color.BLACK);
            }
        }
        if(intent.hasExtra("photo")){
            imageViewPhoto.setImageResource((int)getIntent().getSerializableExtra("photo"));
        }
    }

    public void clickedFB(View v){

        String FACEBOOK_URL = "https://www.facebook.com/" + fb;

        Intent intent;


        if (isPackageInstalled("com.facebook.katana")) {
            String urlToUse = "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToUse));
        } else {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(FACEBOOK_URL));
        }

        // Check if there is an app that can handle fb or https intents
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            makeErrorAlert("No Application found that handles ACTION_VIEW (fb/https) intents");
        }
        Toast.makeText(this, "clicked fb", Toast.LENGTH_SHORT).show();
    }

    public void clickedYT(View v){
        Toast.makeText(this, "clicked YT", Toast.LENGTH_SHORT).show();
        //String name = "Netflix";
        Intent intent = null;
        try {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.youtube");
            intent.setData(Uri.parse("https://www.youtube.com/" + yt));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/" + tw)));
        }

    }
    public void clickedTwitter(View v){
        Toast.makeText(this, "clicked twitter", Toast.LENGTH_SHORT).show();
        Intent intent = null;
        try {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.twitter.android");
            intent.setData(Uri.parse("https://www.twitter.com/" + tw));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com/" + tw)));
        }
    }

    public void clickCall(View v) {
        //String number = "650-253-0000";

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNo));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            makeErrorAlert("No Application found that handles ACTION_DIAL (tel) intents");
        }
    }

    public void clickAddress(View v) {


        Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));

        Intent intent = new Intent(Intent.ACTION_VIEW, mapUri);

        // Check if there is an app that can handle geo intents
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            makeErrorAlert("No Application found that handles ACTION_VIEW (geo) intents");
        }
    }

    public void clickEmail(View v) {
        //String[] addresses = new String[]{"christopher.hield@gmail.com", "some.person@somemail.edu"};

        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));

        intent.putExtra(Intent.EXTRA_EMAIL, email);
        //intent.putExtra(Intent.EXTRA_SUBJECT, "This comes from EXTRA_SUBJECT");
        //intent.putExtra(Intent.EXTRA_TEXT, "Email text body from EXTRA_TEXT...");

        // Check if there is an app that can handle mailto intents
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            makeErrorAlert("No Application found that handles SENDTO (mailto) intents");
        }
    }

    public void goToInfoPage(View v) {
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
            ComponentName componentName = intent.resolveActivity(getPackageManager());


            if (componentName != null) {
                startActivity(intent);
            } else {
                makeErrorAlert("No Application found that handles ACTION_VIEW (https) intents");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public boolean isPackageInstalled(String packageName) {
        try {
            return getPackageManager().getApplicationInfo(packageName, 0).enabled;
        }
        catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void makeErrorAlert(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(msg);
        builder.setTitle("No App Found");

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}