package com.example.movieandroidapp.service.Review;

import com.example.movieandroidapp.contract.review.getListReviewContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.BodyRequest.ChangePasswordBody;
import com.example.movieandroidapp.network.Review.IReviewApi;
import com.example.movieandroidapp.network.User.IUserApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllReviewsOfMovieRequest implements getListReviewContract.Model{
    @Override
    public void GetAllReviewsOfMovie(OnFinishedListener onFinishedListener, String idMovie) {
        IReviewApi apiService = ApiClient.getClient().create(IReviewApi.class);
        Call<ApiResponse<List<Review>>> call = apiService.GetAllReviewsOfMovie(idMovie);

        call.enqueue(new Callback<ApiResponse<List<Review>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Review>>> call, Response<ApiResponse<List<Review>>> response) {
                if (response.isSuccessful()) {
                    onFinishedListener.onFinished(response.body().getData());
                } else {
                    onFinishedListener.onFailure(response.body().getMessage());
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<List<Review>>> call, Throwable t) {
                onFinishedListener.onFailure("Error on get list review");
            }
        });
    }
}
