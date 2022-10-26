package com.example.movieandroidapp.network.Review;

import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.network.BodyRequest.ReviewBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IReviewApi {

    @GET(ReviewApiUrl.GetAllReviewsOfMovie)
    Call<ApiResponse<List<Review>>> GetAllReviewsOfMovie(@Query("MovieID") String movieID);

    @POST(ReviewApiUrl.CreateReview)
    Call<ApiResponse<String>> CreateReview(@Body ReviewBody review);

    @GET(ReviewApiUrl.GetTopLatestReview)
    Call<ApiResponse<List<Review>>> GetTopLatestReview(@Query("top") String top);
}
