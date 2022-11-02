package com.example.movieandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.example.movieandroidapp.Activity.AdminActivity;
import com.example.movieandroidapp.Activity.HomeActivity;
import com.example.movieandroidapp.Activity.LoginActivity;
import com.example.movieandroidapp.Activity.RegisterActivity;
import com.example.movieandroidapp.Utility.DataLocalManager;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.model.PayLoadToken;

public class MainActivity extends AppCompatActivity {

//    private Button btnLogin, btnRegister, btnHomeView, btnHomeAdmin;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        btnLogin = findViewById(R.id.btnLoginView);
//        btnRegister = findViewById(R.id.btnRegisterView);
//        btnHomeView = findViewById(R.id.btnHomeView);
//        btnHomeAdmin = findViewById(R.id.btnHomeAdmin);


//        btnLogin.setOnClickListener(v -> {
//            startActivity(new Intent(this, LoginActivity.class));
//        });
//        btnRegister.setOnClickListener(v -> {
//            startActivity(new Intent(this, RegisterActivity.class));
//        });
//        btnHomeView.setOnClickListener(v -> {
//            startActivity(new Intent(this, HomeActivity.class));
//        });
//        btnHomeAdmin.setOnClickListener(v -> {
//            startActivity(new Intent(this, AdminActivity.class));
//        });

        new Handler().postDelayed(() -> {
            token = DataLocalManager.getAccessToken();
            if (token == null || token.isEmpty()) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            } else {
                String[] decoded = Extension.decodeAccessToken(token);
                PayLoadToken payload = Extension.GsonUtil().fromJson(decoded[1], PayLoadToken.class);
                if (Extension.checkRoleIsUser(payload.getRole())) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, AdminActivity.class));
                }
            }
            finish();
        },2000);

    }
}