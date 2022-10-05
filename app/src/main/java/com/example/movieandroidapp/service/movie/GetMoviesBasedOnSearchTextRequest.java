package com.example.movieandroidapp.service.movie;

import com.example.movieandroidapp.contract.movie.GetTopLastestPublicationMoviesContract;
import com.example.movieandroidapp.contract.movie.SearchMovieHome;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.Movie.IMovieApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMoviesBasedOnSearchTextRequest implements SearchMovieHome.Model {
    @Override
    public void getMoviesBasedOnSearchText(OnFinishedListener onFinishedListener, String query) {
        IMovieApi apiService = ApiClient.getClient().create(IMovieApi.class);
        Call<ApiResponse<List<Movie>>> call = apiService.GetMoviesBasedOnSearchText(query);

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
