package com.uofthacks.sosapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;

import java.util.HashMap;
import java.util.Map;

public class FAR_MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_far__maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();

        // PERSON IN NEED OF AID
        double latitude = intent.getDoubleExtra(MainActivity.LATITUDE, 0);
        double longitude = intent.getDoubleExtra(MainActivity.LONGITUDE,0);

        Map<String,Object> params = new HashMap<String, Object>();
        params.put("latitude",latitude);
        params.put("longitude",longitude);
        params.put("situation","");
        ParseCloud.callFunctionInBackground("getClosestResponder", params, new FunctionCallback<Object>() {
            @Override
            public void done(Object object, ParseException e) {
                Log.d("Get Closest Responder", "done: yoyo");
            }
        });



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();

        // PERSON IN NEED OF AID
        double latitude = intent.getDoubleExtra(MainActivity.LATITUDE, 0);
        double longitude = intent.getDoubleExtra(MainActivity.LONGITUDE,0);

        // CURRENT PERSON
        double myLatitude = 43.7;
        double myLongitude = -79.3;

        LatLng personInNeedMarker = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(personInNeedMarker).title("Person Needs Help!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(personInNeedMarker));

        LatLng FAR = new LatLng(myLatitude, myLongitude);
        mMap.addMarker(new MarkerOptions().position(FAR).title("My Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(FAR));
    }
}
