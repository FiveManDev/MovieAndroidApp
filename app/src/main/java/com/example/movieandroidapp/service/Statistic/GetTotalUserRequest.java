package com.example.movieandroidapp.service.Statistic;

import com.example.movieandroidapp.contract.GetTotalContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.Movie.IMovieApi;
import com.example.movieandroidapp.network.Statistic.IStatisticApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTotalUserRequest implements GetTotalContract.Model {
    @Override
    public void getTotal(OnFinishedListener onFinishedListener) {
        IStatisticApi apiService = ApiClient.getClient().create(IStatisticApi.class);
        Call<ApiResponse<Integer>> call = apiService.GetTotalUser();
        call.enqueue(new Callback<ApiResponse<Integer>>() {
            @Override
            public void onResponse(Call<ApiResponse<Integer>> call, Response<ApiResponse<Integer>> response) {
                if (response.isSuccessful()) {
                    onFinishedListener.onFinished(response.body().getData());
                } else {
                    onFinishedListener.onFailure("Don't have any user");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Integer>> call, Throwable t) {
                onFinishedListener.onFailure("Error in Get user");
            }
        });
    }
}
