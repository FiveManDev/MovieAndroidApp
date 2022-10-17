package com.example.movieandroidapp.network.User;

import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.TokenModel;
import com.example.movieandroidapp.network.BodyRequest.CreateUserBody;
import com.example.movieandroidapp.network.BodyRequest.LoginUserBody;
import com.example.movieandroidapp.network.BodyRequest.ResetPassword;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUserApi {

    @POST(UserApiUrl.Login)
    Call<ApiResponse<TokenModel>> login(@Body LoginUserBody loginUserBody);

    @POST(UserApiUrl.CreateUser)
    Call<ApiResponse<String[]>> createUser(@Body CreateUserBody createUserBody);

    @POST(UserApiUrl.ConfirmEmail)
    Call<ApiResponse<String>> confirmEmail(@Body String email_address);

    @POST(UserApiUrl.ConfirmEmailForgot)
    Call<ApiResponse<String>> confirmEmailForgot(@Body String email_address);

    @POST(UserApiUrl.ResetPassword)
    Call<ApiResponse<String[]>> resetPassword(@Body ResetPassword resetPassword);
}
