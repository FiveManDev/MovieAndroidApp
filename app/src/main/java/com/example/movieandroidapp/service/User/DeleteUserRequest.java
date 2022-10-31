package com.example.movieandroidapp.service.User;

import com.example.movieandroidapp.contract.user.ChangeUserStatus;
import com.example.movieandroidapp.contract.user.DeleteUserContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.BodyRequest.CreateUserBody;
import com.example.movieandroidapp.network.User.IUserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteUserRequest implements DeleteUserContract.Model {
    @Override
    public void deleteUserById(DeleteUserContract.Model.OnFinishedListener onFinishedListener, String UserId) {
        IUserApi apiService = ApiClient.getClient().create(IUserApi.class);
        Call<ApiResponse<String[]>> call = apiService.DeleteUser(UserId);

        call.enqueue(new Callback<ApiResponse<String[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<String[]>> call, Response<ApiResponse<String[]>> response) {
                try {
                    if (response.body().isSuccess()) {
                        onFinishedListener.onFinished();
                    } else {
                        onFinishedListener.onFailure("User not found");
                    }
                } catch (Exception e) {
                    onFinishedListener.onFailure("Error when delete user");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<String[]>> call, Throwable t) {
                onFinishedListener.onFailure("Error when delete user");
            }
        });
    }
}
