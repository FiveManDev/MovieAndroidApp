package com.example.movieandroidapp.network.Statistic;

import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.model.Statistic;
import com.example.movieandroidapp.network.BodyRequest.ReviewBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IStatisticApi {
    @GET(StatisticApiUrl.GetStatisticsForMonth)
    Call<ApiResponse<Statistic>> GetStatisticsForMonth(@Query("date") String monthAndYear);

    @GET(StatisticApiUrl.getTotalUser)
    Call<ApiResponse<Integer>> GetTotalUser();

    @GET(StatisticApiUrl.getTotalReview)
    Call<ApiResponse<Integer>> GetTotalReview();
}
