package com.example.movieandroidapp.service.User;

import android.util.Log;

import com.example.movieandroidapp.contract.user.GetUserInformationContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.User.CreateUserBody;
import com.example.movieandroidapp.network.User.IUserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserInformationRequest implements GetUserInformationContract.Model {

    @Override
    public void getUserInformation(OnFinishedListener onFinishedListener,String id) {

        IUserApi apiService = ApiClient.getClient().create(IUserApi.class);
        Call<ApiResponse<User>> call = apiService.getUserInformation(id);

        call.enqueue(new Callback<ApiResponse<User>>() {
            @Override
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                try {
                    if (response.isSuccessful()) {
                        onFinishedListener.onFinished(response.body().getData());
                    } else {
                        onFinishedListener.onFailure("Unauthorized");
                    }
                } catch (Exception e) {
                    onFinishedListener.onFailure("Unauthorized");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {

                onFinishedListener.onFailure("Unauthorized");
            }
        });
    }
}
