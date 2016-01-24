package com.uofthacks.HippoApp;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.widget.TextView;
import android.widget.Toast;

import com.uofthacks.HippoApp.R;

public class HelpOnway extends AppCompatActivity implements LocationListener {

    private TextView latitudeField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_onway);

        latitudeField = (TextView) findViewById(R.id.lat);
        longitudeField = (TextView) findViewById(R.id.lon);

        // Get the location manager, and....
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        // Try getting provider.

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        if (provider == null) {
            latitudeField.setText("Location not available");
            longitudeField.setText("Location not available");
        } else {

            // Handle security exception, and... initialize the location fields
            try {
                Location location = locationManager.getLastKnownLocation(provider);
                System.out.println("Provider " + provider + " has been selected.");
                onLocationChanged(location); // ...this also sets the text view

            } catch (SecurityException e) {
                latitudeField.setText("Location not available");
                longitudeField.setText("Location not available");
            }

        }





        /*
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            latitudeField.setText("Location not available");
            longitudeField.setText("Location not available");
        } */

    }



    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLocationChanged(Location location) {
        int lat = (int) (location.getLatitude());
        int lng = (int) (location.getLongitude());
        latitudeField.setText(String.valueOf(lat));
        longitudeField.setText(String.valueOf(lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    public void packageLocation(String provider) {

    }
}
