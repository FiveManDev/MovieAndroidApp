package com.example.movieandroidapp.service.Review;

import com.example.movieandroidapp.contract.review.GetReviews;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.BodyRequest.Filter;
import com.example.movieandroidapp.network.Review.IReviewApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetReviewsRequest implements GetReviews.Model {
    @Override
    public void  getReviews(OnFinishedListener onFinishedListener, Filter filters) {
        IReviewApi apiService = ApiClient.getClient().create(IReviewApi.class);
        Call<ApiResponse<ResponseFilter<Review[]>>> call = apiService.GetReviews(
                filters.getPageIndex(),
                filters.getPageSize(),
                filters.getQuery(),
                filters.getSortBy(),
                filters.getSortType()
        );

        call.enqueue(new Callback<ApiResponse<ResponseFilter<Review[]>>>() {
            @Override
            public void onResponse(Call<ApiResponse<ResponseFilter<Review[]>>> call, Response<ApiResponse<ResponseFilter<Review[]>>> response) {
                if(response.isSuccessful()){
                    onFinishedListener.onFinished(response.body().getData());
                }
                else{
                    onFinishedListener.onFailure("Error in get reviews");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ResponseFilter<Review[]>>> call, Throwable t) {
                onFinishedListener.onFailure("Error in get reviews");
            }
        });
    }
}
