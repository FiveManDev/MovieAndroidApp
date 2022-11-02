package com.example.movieandroidapp.service.Review;

import com.example.movieandroidapp.contract.review.GetTopLatestReviewContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.Review.IReviewApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTopLatestReviewRequest implements GetTopLatestReviewContract.Model {
    @Override
    public void getTopLatestReview(OnFinishedListener onFinishedListener, String top) {
        IReviewApi apiService = ApiClient.getClient().create(IReviewApi.class);
        Call<ApiResponse<List<Review>>> call = apiService.GetTopLatestReview(top);

        call.enqueue(new Callback<ApiResponse<List<Review>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Review>>> call, Response<ApiResponse<List<Review>>> response) {
                if (response.isSuccessful()) {
                    onFinishedListener.onFinished(response.body().getData());
                } else {
                    onFinishedListener.onFailure("Error on get latest list review");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<List<Review>>> call, Throwable t) {
                onFinishedListener.onFailure("Error on get latest list review");
            }
        });
    }
}
