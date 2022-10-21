package com.example.movieandroidapp.service.User;


import com.example.movieandroidapp.contract.user.LoginContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.TokenModel;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.User.IUserApi;
import com.example.movieandroidapp.network.BodyRequest.LoginUserBody;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRequest implements LoginContract.Model {

    @Override
    public void login(LoginContract.Model.OnFinishedListener onFinishedListener, String userName, String password) {
        IUserApi apiService = ApiClient.getClient().create(IUserApi.class);

        Call<ApiResponse<TokenModel>> call = apiService.login(new LoginUserBody(userName, password));

        call.enqueue(new Callback<ApiResponse<TokenModel>>() {
            @Override
            public void onResponse(Call<ApiResponse<TokenModel>> call, Response<ApiResponse<TokenModel>> response) {
                try {
                    TokenModel tokenModel = response.body().getData();
                    onFinishedListener.onFinished(tokenModel);
                } catch (Exception e) {
                    onFinishedListener.onFailure("Incorrect username or password!");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<TokenModel>> call, Throwable t) {
                onFinishedListener.onFailure("Incorrect username or password!");
            }
        });
    }
}
