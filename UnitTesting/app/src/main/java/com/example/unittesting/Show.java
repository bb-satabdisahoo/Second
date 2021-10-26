package com.example.unittesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.unittesting.databinding.ActivityShowBinding;

public class Show extends AppCompatActivity {

    //SharedPreferences sh;
    ActivityShowBinding activityShowBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_show);
        activityShowBinding = ActivityShowBinding.inflate(getLayoutInflater());
        View view = activityShowBinding.getRoot();
        setContentView(view);

        //sh = getSharedPreferences("shared", MODE_PRIVATE);
        //String name = sh.getString("Name", "");
        //String password = sh.getString("Password", "");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String name = extras.getString("Name");
        String password = extras.getString("Password");

        activityShowBinding.valueName.setText(name);
        activityShowBinding.valuePassword.setText(password);
    }
}