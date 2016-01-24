package com.uofthacks.sosapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String SEVERITY = "com.uofthacks.sosapp.SEVERITY";
    public static final String LATITUDE = "com.uofthacks.sosapp.LATITUDE";
    public static final String LONGITUDE = "com.uofthacks.sosapp.LONGITUDE";

    private static boolean isParseEnabled = false;
    // Send message to patient ack
    public void sendMessage(View view) {
        Intent intent = new Intent(this, FAR_MapsActivity.class);
        Random r = new Random();

        double randomLat = -0.25 + (0.25 - -0.25) * r.nextDouble();
        double randomLong = -0.25  + (0.25 - -0.25) * r.nextDouble();
        intent.putExtra(SEVERITY, ""+view.getTag());
        intent.putExtra(LATITUDE,43.7 + randomLat);
        intent.putExtra(LONGITUDE,-79.4 + randomLong );
        startActivity(intent);
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public void getHelp(View view) {
        Intent intent = new Intent(this, FARGPS.class);
        Random r = new Random();
        int randomNumber = r.nextInt(2 - 0 + 1) + 2;
        double randomLat = -0.25 + (0.25 - -0.25) * r.nextDouble();
        double randomLong = -0.25  + (0.25 - -0.25) * r.nextDouble();
        String r_severity = randomNumber == 3 ? "immediate" : ( randomNumber == 2 ? "medium" : "mild");
        intent.putExtra(SEVERITY, r_severity);
        intent.putExtra(LATITUDE,43.7 + randomLat);
        intent.putExtra(LONGITUDE,-79.4 + randomLong );
        startActivity(intent);

    }

    protected void onCreate(Bundle savedInstanceState) {
        if (!isParseEnabled){
            Parse.enableLocalDatastore(this);

            Parse.initialize(this, "ebWQIMSb18BQ4MF3hKUNeMxdg3v8IN98YB5Tpje5", "hWXefeWUWCum3hIcrbZjZcwlk4z05Ms4LjOTF5VZ");
            ParseInstallation.getCurrentInstallation().saveInBackground();
            isParseEnabled = true;
        }



        GPSTracker gps = new GPSTracker(this);

        if (!gps.isGPSEnabled)
            gps.showSettingsAlert();

        super.onCreate(savedInstanceState);
//        Parse.initialize(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.uofthacks.sosapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.uofthacks.sosapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}
