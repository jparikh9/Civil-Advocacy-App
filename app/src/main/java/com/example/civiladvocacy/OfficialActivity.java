package com.example.civiladvocacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class OfficialActivity extends AppCompatActivity {

    String email = "";
    private String phoneNo;
    private String website;
    private String address;
    TextView textView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official);
        textViewName = findViewById(R.id.name_officialactivity_vid);
        textView = findViewById(R.id.emailid_officialactivity_id);
        textViewPhoneNo = findViewById(R.id.phoneNo_officialactivity_vid);
        textViewWebsite = findViewById(R.id.url_officialactivity_vid);
        textViewAddress = findViewById(R.id.addressvalue_officialactivity_vid);
        textViewPhoneTitle = findViewById(R.id.phone_officialactivity_vid);
        imageViewPhoto = findViewById(R.id.photo_officialactivity_vid);
        textViewAddressTitle = findViewById(R.id.addresstitle_officialactivity_vid);
        textViewWebsiteTitle = findViewById(R.id.website_officialactivity_vid);
        facebook = findViewById(R.id.facebook_officialactivity_vid);
        twitter = findViewById(R.id.twitter_officialactivity_vid);
        youtube = findViewById(R.id.youtube_officialactivity_vid);
        location = findViewById(R.id.location_officialactivity_vid);
        Intent intent = getIntent();
        if(intent.hasExtra("location")){
            location.setText((String) getIntent().getSerializableExtra("location"));
        }
        /*if(intent.hasExtra("twitter")){

        }
        else{
            twitter.setVisibility(View.GONE);
        }*/
        if(intent.hasExtra("facebook")){

        }
        else{
            facebook.setVisibility(View.GONE);
        }
        if(intent.hasExtra("youtube")){

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
        if(intent.hasExtra("photo")){
            imageViewPhoto.setImageResource((int)getIntent().getSerializableExtra("photo"));
        }
    }

    public void clickedFB(View v){
        Toast.makeText(this, "clicked fb", Toast.LENGTH_SHORT).show();
    }

    public void clickedYT(View v){
        Toast.makeText(this, "clicked YT", Toast.LENGTH_SHORT).show();
    }
    public void clickedTwitter(View v){
        Toast.makeText(this, "clicked twitter", Toast.LENGTH_SHORT).show();
    }
}