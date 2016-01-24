package com.uofthacks.HippoApp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;

public class StartPage extends AppCompatActivity {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        final Intent intent = new Intent(this, Legal.class);
        mHandler.postDelayed(new Runnable() {
            public void run() {
                startActivity(intent);
            }
        }, 3500);

    }

}
