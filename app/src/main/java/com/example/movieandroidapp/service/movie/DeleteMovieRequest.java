package com.example.movieandroidapp.service.movie;

import com.example.movieandroidapp.contract.movie.DeleteMovieContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.Pagination;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.Movie.IMovieApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteMovieRequest implements DeleteMovieContract.Model {
    @Override
    public void deleteMovieById(OnFinishedListener onFinishedListener, String id) {
        IMovieApi apiService = ApiClient.getClient().create(IMovieApi.class);
        Call<ApiResponse<String[]>> call = apiService.DeleteMovie(id);
        call.enqueue(new Callback<ApiResponse<String[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<String[]>> call, Response<ApiResponse<String[]>> response) {
                if (response.isSuccessful()) {
                    onFinishedListener.onFinished();
                } else {
                    onFinishedListener.onFailure("Don't have any movie");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String[]>> call, Throwable t) {
                onFinishedListener.onFailure("Error in Delete movies");
            }
        });
    }
}
