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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.Parse;
import com.parse.ParseGeoPoint;

import java.util.Random;

public class FARGPS extends AppCompatActivity {
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fargps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String severity =  intent.getStringExtra(MainActivity.SEVERITY);
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.farInfo);
        if (severity.equals("medium")){
            rl.setBackgroundColor(Color.YELLOW);
        }
        else if (severity.equals("immediate")){
            rl.setBackgroundColor(Color.RED);
            TextView status911 = (TextView) findViewById(R.id.status911);
            status911.setText("911 Dispatched");
        }
        else{
            rl.setBackgroundColor(Color.GREEN);
        }

        GPSTracker gps = new GPSTracker(this);
        if (!gps.isGPSEnabled)
            gps.showSettingsAlert();
        // TODO: NEED TO FIX THIS
        else{
//            if (gps.canGetLocation){
                double latitude = 46;//gps.getLatitude();
                double longitude = 35;//gps.getLongitude();
                Random r = new Random();
                int randomNumber = r.nextInt( 75 - 3 + 1) + 3;
                int age = randomNumber;
                int randomSex = r.nextInt(2-0+1)+0;
                String r_sex = randomSex == 1 ? "M" : "F";
//                String sex = "M";
                TextView tv1 = (TextView) findViewById(R.id.ageValue);
                TextView tv2 = (TextView) findViewById(R.id.sexValue);

                tv1.setText(""+age);
                tv2.setText(r_sex);
//            }else
//            Log.d("gps","Cannot retrieve GPS location right now.");
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

            Intent intent = getIntent();
            // PERSON IN NEED OF AID
            double latitude = intent.getDoubleExtra(MainActivity.LATITUDE, 0);
            double longitude = intent.getDoubleExtra(MainActivity.LONGITUDE,0);

            // CURRENT PERSON
            double myLatitude = 43.7;
            double myLongitude = -79.35;

            LatLng personInNeedMarker = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(personInNeedMarker).title("Person Needs Help!")).showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(personInNeedMarker));

            LatLng FAR = new LatLng(myLatitude, myLongitude);
            mMap.addMarker(new MarkerOptions().position(FAR).title("My Current Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.places_ic_clear))).showInfoWindow();
            ParseGeoPoint parsePerson = new ParseGeoPoint(latitude,longitude);
            ParseGeoPoint parseMe = new ParseGeoPoint(myLatitude,myLongitude);

            double distanceTo = parsePerson.distanceInKilometersTo(parseMe);

            // Animate camera to zoom in closer
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 12.0f));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(FAR));
            TextView distanceText = (TextView)findViewById(R.id.distance);
            distanceText.setText(String.format("%.2f km",distanceTo));
        }
    }
}
