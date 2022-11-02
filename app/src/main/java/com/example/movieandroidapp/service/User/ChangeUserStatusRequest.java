package com.example.movieandroidapp.service.User;

import com.example.movieandroidapp.contract.user.ChangeUserStatus;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.BodyRequest.ChangePasswordBody;
import com.example.movieandroidapp.network.BodyRequest.ChangeStatusUser;
import com.example.movieandroidapp.network.User.IUserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeUserStatusRequest implements ChangeUserStatus.Model {

    @Override
    public void changeUserStatus(OnFinishedListener onFinishedListener, String USerId, boolean isBanned) {
        IUserApi apiService = ApiClient.getClient().create(IUserApi.class);
        Call<ApiResponse<String[]>> call = apiService.ChangeUserStatus(new ChangeStatusUser(USerId,isBanned));

        call.enqueue(new Callback<ApiResponse<String[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<String[]>> call, Response<ApiResponse<String[]>> response) {
                if(response.isSuccessful()){
                    onFinishedListener.onFinished(USerId);
                }
                else{
                    onFinishedListener.onFailure("Error on change status user");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<String[]>> call, Throwable t) {
                onFinishedListener.onFailure("Error on change status user");
            }
        });
    }
}
