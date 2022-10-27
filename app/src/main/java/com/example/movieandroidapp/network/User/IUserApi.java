package com.example.movieandroidapp.network.User;

import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.TokenModel;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.network.BodyRequest.ChangePasswordBody;
import com.example.movieandroidapp.network.BodyRequest.CreateUserBody;
import com.example.movieandroidapp.network.BodyRequest.LoginUserBody;
import com.example.movieandroidapp.network.BodyRequest.ResetPassword;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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

    @POST(UserApiUrl.ChangePassword)
    Call<ApiResponse<String[]>> changePassword(@Body ChangePasswordBody passwordBody);
    @GET(UserApiUrl.GetUsers)
    Call<ApiResponse<ResponseFilter<User[]>>> GetUsers(@Query("pageIndex") int pageIndex,
                                                       @Query("pageSize") int pageSize,
                                                       @Query("q") String q,
                                                       @Query("sortBy") String sortBy,
                                                       @Query("sortType") String sortType);
}
