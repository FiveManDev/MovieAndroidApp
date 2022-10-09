package com.example.movieandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.movieandroidapp.Activity.HomeActivity;
import com.example.movieandroidapp.Activity.LoginActivity;
import com.example.movieandroidapp.Activity.RegisterActivity;

public class MainActivity extends AppCompatActivity  {

    private Button btnLogin,btnRegister,btnHomeView;
    String urlmovie = "https://www.youtube.com/watch?v=5TKX8yBTUeY&ab_channel=CodeEngineer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLoginView);
        btnRegister = findViewById(R.id.btnRegisterView);
        btnHomeView = findViewById(R.id.btnHomeView);
        playVideo();
        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
        btnHomeView.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
        });

    }
    private void playVideo(){
        try {


        }catch (Exception e){

        }
    }

}