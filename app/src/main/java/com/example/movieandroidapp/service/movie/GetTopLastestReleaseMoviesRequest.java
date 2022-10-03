package com.example.movieandroidapp.service.movie;

import com.example.movieandroidapp.contract.movie.GetTopLastestReleaseMoviesContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.Movie.IMovieApi;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTopLastestReleaseMoviesRequest implements GetTopLastestReleaseMoviesContract.Model {

    @Override
    public void getTopLastestReleaseMovies(OnFinishedListener onFinishedListener, int top) {
        IMovieApi apiService = ApiClient.getClient().create(IMovieApi.class);
        Call<ApiResponse<List<Movie>>> call = apiService.getTopLastestReleaseMovies(top);

        call.enqueue(new Callback<ApiResponse<List<Movie>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Movie>>> call, Response<ApiResponse<List<Movie>>> response) {
                if(response.isSuccessful()){
                    onFinishedListener.onFinished(response.body().getData());
                }
                else{
                    onFinishedListener.onFailure("Dont have any movie");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<List<Movie>>> call, Throwable t) {
                onFinishedListener.onFailure("Can not fetch data");
            }
        });
    }

}
