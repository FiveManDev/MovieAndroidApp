package com.example.movieandroidapp.network.Profile;

import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IProfileApi {

    @GET(ProfileApiUrl.GetUserInformation)
    Call<ApiResponse<User>> getUserInformation(@Query("UserID") String id);

}
