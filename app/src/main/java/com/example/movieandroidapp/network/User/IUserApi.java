package com.example.movieandroidapp.network.User;

import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.TokenModel;
import com.example.movieandroidapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IUserApi {

    @POST(UserApiUrl.Login)
    Call<ApiResponse<TokenModel>> login(@Body LoginUserBody loginUserBody);

    @POST(UserApiUrl.CreateUser)
    Call<ApiResponse<String[]>> createUser(@Body CreateUserBody createUserBody);

    @GET(UserApiUrl.GetUserInformation)
    Call<ApiResponse<User>> getUserInformation(@Query("id") String id);
}
