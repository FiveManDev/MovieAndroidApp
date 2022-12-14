package com.example.movieandroidapp.service.movie;

import com.example.movieandroidapp.contract.GetTotalContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.Movie.IMovieApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTotalMoviesRequest implements GetTotalContract.Model {
    @Override
    public void getTotal(OnFinishedListener onFinishedListener) {
        IMovieApi apiService = ApiClient.getClient().create(IMovieApi.class);
        Call<ApiResponse<Integer>> call = apiService.GetTotalMovies();
        call.enqueue(new Callback<ApiResponse<Integer>>() {
            @Override
            public void onResponse(Call<ApiResponse<Integer>> call, Response<ApiResponse<Integer>> response) {
                if (response.isSuccessful()) {
                    onFinishedListener.onFinished(response.body().getData());
                } else {
                    onFinishedListener.onFailure("Don't have any movie");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Integer>> call, Throwable t) {
                onFinishedListener.onFailure("Error in Get movies");
            }
        });
    }
}
