package com.example.movieandroidapp.service.movie;

import com.example.movieandroidapp.contract.movie.GetGenre;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.Genre;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.Movie.IMovieApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllGenreOfMovieRequest implements GetGenre.Model {

    @Override
    public void getGenre(OnFinishedListener onFinishedListener) {
        IMovieApi apiService = ApiClient.getClient().create(IMovieApi.class);
        Call<ApiResponse<List<String>>> call = apiService.GetAllGenreOfMovie();

        call.enqueue(new Callback<ApiResponse<List<String>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<String>>> call, Response<ApiResponse<List<String>>> response) {
                if(response.isSuccessful()){
                    List<String> stringList =response.body().getData();
                    List<Genre> genreList= new ArrayList<>();
                    for (String item:
                         stringList) {
                        genreList.add(new Genre(item));
                    }
                    onFinishedListener.onFinished(genreList);
                }
                else{
                    onFinishedListener.onFailure("Don't have any genre");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<List<String>>> call, Throwable t) {
                onFinishedListener.onFailure("Can not fetch genre");
            }
        });
    }
}
