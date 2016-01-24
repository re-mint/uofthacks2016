package com.uofthacks.sosapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FARGPS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fargps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String severity =  intent.getStringExtra(MainActivity.SEVERITY);
        Log.d("test",severity);
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.farInfo);
        if (severity.equals("medium")){
            rl.setBackgroundColor(Color.YELLOW);
        }
        else if (severity.equals("immediate")){
            rl.setBackgroundColor(Color.RED);
        }
        else{
            rl.setBackgroundColor(Color.GREEN);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        GPSTracker gps = new GPSTracker(this);
        if (!gps.isGPSEnabled)
            gps.showSettingsAlert();
        // TODO: NEED TO FIX THIS
        else{
//            if (gps.canGetLocation){
                double latitude = 46;//gps.getLatitude();
                double longitude = 35;//gps.getLongitude();
                int age = 21;
                String sex = "M";
                TextView tv1 = (TextView) findViewById(R.id.ageValue);
                TextView tv2 = (TextView) findViewById(R.id.sexValue);

                tv1.setText(""+age);
                tv2.setText(sex);
//            }else
//            Log.d("gps","Cannot retrieve GPS location right now.");
        }

    }

}
