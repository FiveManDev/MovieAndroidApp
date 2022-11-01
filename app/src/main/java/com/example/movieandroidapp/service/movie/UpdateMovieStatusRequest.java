package com.example.movieandroidapp.service.movie;

import com.example.movieandroidapp.contract.movie.ChangeStatusMovieContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.BodyRequest.ChangeStatusBody;
import com.example.movieandroidapp.network.Movie.IMovieApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMovieStatusRequest implements ChangeStatusMovieContract.Model {
    @Override
    public void changeStatusMovie(OnFinishedListener onFinishedListener, ChangeStatusBody statusBody) {
        IMovieApi apiService = ApiClient.getClient().create(IMovieApi.class);
        Call<ApiResponse<String[]>> call = apiService.UpdateMovieStatus(statusBody);
        call.enqueue(new Callback<ApiResponse<String[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<String[]>> call, Response<ApiResponse<String[]>> response) {
                if (response.isSuccessful()) {
                    onFinishedListener.onFinished();
                } else {
                    onFinishedListener.onFailure("Cannot change status movie");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String[]>> call, Throwable t) {
                onFinishedListener.onFailure("Error in update movies");
            }
        });
    }
}
