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

//        //get recyle view
//        rvMovieList = findViewById(R.id.rvMovieList);
//        pbLoading = findViewById(R.id.pbLoading);
//
//        movieList = new ArrayList<>();
//
//        layoutManager = new LinearLayoutManager(this);
//
//        rvMovieList.setLayoutManager(layoutManager);
//        rvMovieList.setHasFixedSize(true);
//
//        moviePresenter = new MoviePresenter(this);
//        moviePresenter.requestDataFromServer();

    }

//    @Override
//    public void showProgress() {
//        pbLoading.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void hideProgress() {
//        pbLoading.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void setDataToRecyclerview(List<Movie> movieListArray) {
//        movieList.addAll(movieListArray);
//        movieListAdapter = new MovieListAdapter(movieList);
//        rvMovieList.setAdapter(movieListAdapter);
//    }
//
//    @Override
//    public void onResponseFailure(Throwable throwable) {
//        Log.e("ERROR:", throwable.getMessage());
//        Toast.makeText(MainActivity.this, "Error in getting data", Toast.LENGTH_LONG).show();
//    }
}