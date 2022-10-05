package com.example.movieandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.movieandroidapp.Activity.HomeActivity;
import com.example.movieandroidapp.Activity.LoginActivity;
import com.example.movieandroidapp.Activity.RegisterActivity;
import com.example.movieandroidapp.Utility.DataLocalManager;

public class MainActivity extends AppCompatActivity  {

//    private MoviePresenter moviePresenter;
//    private RecyclerView rvMovieList;
//    private List<Movie> movieList;
//    private MovieListAdapter movieListAdapter;
//    private ProgressBar pbLoading;
//    private LinearLayoutManager layoutManager;
    private Button btnLogin,btnRegister,btnHomeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLoginView);
        btnRegister = findViewById(R.id.btnRegisterView);
        btnHomeView = findViewById(R.id.btnHomeView);


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

}