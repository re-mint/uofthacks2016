package com.uofthacks.HippoApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.uofthacks.HippoApp.R;

public class Legal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal);
    }

    public void onClickAcceptedTerms(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
