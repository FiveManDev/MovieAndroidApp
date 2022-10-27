package com.example.movieandroidapp.service.User;

import com.example.movieandroidapp.contract.user.GetUsersContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.BodyRequest.Filter;
import com.example.movieandroidapp.network.User.IUserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUsersRequest implements GetUsersContract.Model {
    @Override
    public void getUsers(OnFinishedListener onFinishedListener, Filter filters) {
        IUserApi apiService = ApiClient.getClient().create(IUserApi.class);
        Call<ApiResponse<ResponseFilter<User[]>>> call = apiService.GetUsers(
                filters.getPageIndex(),
                filters.getPageSize(),
                filters.getQuery(),
                filters.getSortBy(),
                filters.getSortType()
        );

        call.enqueue(new Callback<ApiResponse<ResponseFilter<User[]>>>() {
            @Override
            public void onResponse(Call<ApiResponse<ResponseFilter<User[]>>> call, Response<ApiResponse<ResponseFilter<User[]>>> response) {
                if(response.isSuccessful()){
                    onFinishedListener.onFinished(response.body().getData());
                }
                else{
                    onFinishedListener.onFailure("Error in get users");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ResponseFilter<User[]>>> call, Throwable t) {
                onFinishedListener.onFailure("Error in get users");
            }
        });
    }
}
