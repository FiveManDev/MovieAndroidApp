package com.example.movieandroidapp.service.User;

import com.example.movieandroidapp.contract.user.CreateAccountContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.BodyRequest.CreateUserBody;
import com.example.movieandroidapp.network.User.IUserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserRequest implements CreateAccountContract.Model {
    @Override
    public void register(OnFinishedListener onFinishedListener, String userName, String email, String password) {
        CreateUserBody createUserBody =new CreateUserBody(userName,email,password);
        IUserApi apiService = ApiClient.getClient().create(IUserApi.class);
        Call<ApiResponse<String[]>> call = apiService.createUser(createUserBody);

        call.enqueue(new Callback<ApiResponse<String[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<String[]>> call, Response<ApiResponse<String[]>> response) {
                try {
                    if (response.body().isSuccess()) {
                        onFinishedListener.onFinished();
                    } else {
                        onFinishedListener.onFailure(response.body().getMessage());
                    }
                } catch (Exception e) {
                    onFinishedListener.onFailure("Error when create account");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<String[]>> call, Throwable t) {
                onFinishedListener.onFailure("Error when create account");
            }
        });
    }
}
