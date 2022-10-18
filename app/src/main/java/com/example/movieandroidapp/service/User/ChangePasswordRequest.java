package com.example.movieandroidapp.service.User;

import android.util.Log;

import com.example.movieandroidapp.contract.user.ChangePasswordContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.BodyRequest.ChangePasswordBody;
import com.example.movieandroidapp.network.User.IUserApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordRequest implements ChangePasswordContract.Model {
    @Override
    public void changePassword(OnFinishedListener onFinishedListener, String oldPassword, String newPassword, String confirmPassword) {
        IUserApi apiService = ApiClient.getClient().create(IUserApi.class);
        Call<ApiResponse<String[]>> call = apiService.changePassword(new ChangePasswordBody(oldPassword, newPassword, confirmPassword));

        call.enqueue(new Callback<ApiResponse<String[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<String[]>> call, Response<ApiResponse<String[]>> response) {
                if (response.code() == 400) {
                    onFinishedListener.onFailure("Old password incorrect");
                } else {
                    onFinishedListener.onFinished();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String[]>> call, Throwable t) {
                onFinishedListener.onFailure("Error on change password");
            }
        });
    }
}
