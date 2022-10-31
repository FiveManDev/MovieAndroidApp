package com.example.movieandroidapp.service.movie;

import com.example.movieandroidapp.contract.movie.CatalogHomeContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.Movie.IMovieApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMovieBaseOnFilterRequest implements CatalogHomeContract.Model {
    @Override
    public void getMovieBaseOnFilter(OnFinishedListener onFinishedListener, String genreID, String quality, String ratingMin, String ratingMax, String releaseTimeMin, String releaseTimeMax) {
        IMovieApi apiService = ApiClient.getClient().create(IMovieApi.class);
        Call<ApiResponse<List<Movie>>> call = apiService.GetMovieBaseOnFilter(genreID, quality, ratingMin, ratingMax, releaseTimeMin, releaseTimeMax);

        call.enqueue(new Callback<ApiResponse<List<Movie>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Movie>>> call, Response<ApiResponse<List<Movie>>> response) {
                if(response.isSuccessful()){
                    onFinishedListener.onFinished(response.body().getData());
                }
                else{
                    onFinishedListener.onFailure("Couldn't find any movies");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<List<Movie>>> call, Throwable t) {
                onFinishedListener.onFailure("Error in catalog");
            }
        });
    }
}
