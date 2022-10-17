package com.example.movieandroidapp.service.User;

import com.example.movieandroidapp.contract.user.ConfirmEmailContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.User.IUserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmEmail implements ConfirmEmailContract.Model {

    @Override
    public void confirmEmail(OnFinishedListener onFinishedListener, String email_address) {
        IUserApi apiService = ApiClient.getClient().create(IUserApi.class);
        Call<ApiResponse<String>> call = apiService.confirmEmail(email_address);

        call.enqueue(new Callback<ApiResponse<String>>() {
            @Override
            public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if(response.body().isSuccess()){
                    onFinishedListener.onFinished(response.body().getData());
                }
                else{
                    onFinishedListener.onFailure(response.body().getMessage());
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                onFinishedListener.onFailure("Error on confirm email");
            }
        });
    }
}
