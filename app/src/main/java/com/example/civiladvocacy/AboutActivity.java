package com.example.civiladvocacy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.content.pm.PackageManager;

public class AboutActivity extends AppCompatActivity {

    private String url = "https://developers.google.com/civic-information/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }


    public void goToInfoPage(View v) {
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
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



    private void makeErrorAlert(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(msg);
        builder.setTitle("No App Found");

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}