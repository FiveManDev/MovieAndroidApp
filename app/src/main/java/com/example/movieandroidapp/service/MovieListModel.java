package com.example.movieandroidapp.service;

import com.example.movieandroidapp.contract.MovieListContract;
import com.example.movieandroidapp.model.Movie;
import com.example.movieandroidapp.model.MovieListResponse;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.Movie.IMovieApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListModel implements MovieListContract.Model {

    @Override
    public void getMovieList(OnFinishedListener onFinishedListener, int pageNo) {
        IMovieApi apiService = ApiClient.getClient().create(IMovieApi.class);

        Call<MovieListResponse> call = apiService.getPopularMovies(ApiClient.API_KEY, pageNo);

        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                List<Movie> movies = response.body().getResults();
                onFinishedListener.onFinished(movies);
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

}
