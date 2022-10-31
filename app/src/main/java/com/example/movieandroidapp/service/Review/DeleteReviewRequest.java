package com.example.movieandroidapp.service.Review;

import com.example.movieandroidapp.contract.review.DeleteReviewContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.Review.IReviewApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteReviewRequest implements DeleteReviewContract.Model {
    @Override
    public void deleteReview(OnFinishedListener onFinishedListener, String reviewId) {
        IReviewApi apiService = ApiClient.getClient().create(IReviewApi.class);
        Call<ApiResponse<String[]>> call = apiService.DeleteReview(reviewId);

        call.enqueue(new Callback<ApiResponse<String[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<String[]>> call, Response<ApiResponse<String[]>> response) {
                if (response.isSuccessful()) {
                    onFinishedListener.onFinished();
                } else {
                    onFinishedListener.onFailure("Can't delete review");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<String[]>> call, Throwable t) {
                onFinishedListener.onFailure("Error on delete review");
            }
        });
    }
}
