package com.example.movieandroidapp.service.Statistic;

import com.example.movieandroidapp.contract.StatisticsContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.model.Statistic;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.Review.IReviewApi;
import com.example.movieandroidapp.network.Statistic.IStatisticApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetStatisticsForMonthRequest implements StatisticsContract.Model {
    @Override
    public void getStatistics(OnFinishedListener onFinishedListener, String monthAndYear) {
        IStatisticApi apiService = ApiClient.getClient().create(IStatisticApi.class);
        Call<ApiResponse<Statistic>> call = apiService.GetStatisticsForMonth(monthAndYear);

        call.enqueue(new Callback<ApiResponse<Statistic>>() {
            @Override
            public void onResponse(Call<ApiResponse<Statistic>> call, Response<ApiResponse<Statistic>> response) {
                if (response.isSuccessful()) {
                    onFinishedListener.onFinished(response.body().getData());
                } else {
                    onFinishedListener.onFailure("Error on get latest list review");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<Statistic>> call, Throwable t) {
                onFinishedListener.onFailure("Error on get latest list review");
            }
        });
    }
}
