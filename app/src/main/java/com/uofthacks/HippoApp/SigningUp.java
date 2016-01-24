package com.uofthacks.HippoApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.uofthacks.HippoApp.R;

public class SigningUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing_up);
    }
    public void onClickSubmit(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Thanks for signing up!", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
