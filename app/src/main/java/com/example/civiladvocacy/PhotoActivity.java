package com.example.civiladvocacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PhotoActivity extends AppCompatActivity {

    TextView location;
    TextView name;
    TextView office;
    ImageView imageViewPhoto;
    ImageView partylogo;
    String partystr;
    ConstraintLayout ct;
    String photourl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        location = findViewById(R.id.address_photo_vid);
        name = findViewById(R.id.textView2);
        office = findViewById(R.id.textView3);
        imageViewPhoto = findViewById(R.id.imageView);
        partylogo = findViewById(R.id.imageView2);
        ct = findViewById(R.id.cl_photo_vid);
        Intent intent = getIntent();

        if(intent.hasExtra("location1")){
            location.setText((String) getIntent().getSerializableExtra("location1"));
        }
        if(intent.hasExtra("official_name1")){
            name.setText((String)getIntent().getSerializableExtra("official_name1"));
        }
        if(intent.hasExtra("official_office1")){
            office.setText((String)getIntent().getSerializableExtra("official_office1"));
        }
        if(intent.hasExtra("party1")){
            //partystr((String)getIntent().getSerializableExtra("party"));
            partystr = (String)getIntent().getSerializableExtra("party1");
            if(partystr.equalsIgnoreCase("Democratic Party") || partystr.equalsIgnoreCase("Democrat party")){
                ct.setBackgroundColor(Color.BLUE);
                partylogo.setImageResource(R.drawable.dem_logo);
            } else if (partystr.equalsIgnoreCase("Republican Party")) {
                ct.setBackgroundColor(Color.RED);
                partylogo.setImageResource(R.drawable.rep_logo);
            }
            else{
                ct.setBackgroundColor(Color.BLACK);
            }
        }
        if(intent.hasExtra("photo1")){
            //imageViewPhoto.setImageResource((int)getIntent().getSerializableExtra("photo"));
            photourl = (String)getIntent().getSerializableExtra("photo1");
            downloadPhoto(photourl);
        }
        else{
            photourl = "";
            downloadPhoto(photourl);
        }
    }

    public void downloadPhoto(String imageurl){


        if(imageurl.isEmpty()){
            Glide.with(this)
                    .load(R.drawable.missing).placeholder(R.drawable.missing)
                    .into(imageViewPhoto);

        }
        else {
            String[] k = imageurl.split("//");
            imageurl = "https://"+ k[1];
            Glide.with(this)
                    .load(imageurl).error(R.drawable.brokenimage).placeholder(R.drawable.missing)
                    .into(imageViewPhoto);


        }
    }
}