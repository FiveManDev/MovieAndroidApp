package com.example.movieandroidapp;

import android.app.Application;
import android.widget.Toast;

import com.example.movieandroidapp.Utility.DataLocalManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
