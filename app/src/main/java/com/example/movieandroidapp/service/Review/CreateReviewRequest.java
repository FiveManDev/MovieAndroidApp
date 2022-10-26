package com.example.movieandroidapp.service.Review;

import com.example.movieandroidapp.contract.review.CreateReviewContract;
import com.example.movieandroidapp.contract.review.getListReviewContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.BodyRequest.ReviewBody;
import com.example.movieandroidapp.network.Review.IReviewApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateReviewRequest implements CreateReviewContract.Model{

    @Override
    public void createReview(OnFinishedListener onFinishedListener, ReviewBody review) {
        IReviewApi apiService = ApiClient.getClient().create(IReviewApi.class);
        Call<ApiResponse<String>> call = apiService.CreateReview(review);

        call.enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful()) {
                    onFinishedListener.onFinished();
                } else {
                    onFinishedListener.onFailure(response.body().getMessage());
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                onFinishedListener.onFailure("Error on get list review");
            }
        });
    }
}
