package com.example.movieandroidapp.service.Profile;

import com.example.movieandroidapp.contract.profile.UpdateProfileUser;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.BodyRequest.UpdateProfile;
import com.example.movieandroidapp.network.Profile.IProfileApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateProfileUserRequest implements UpdateProfileUser.Model {
    @Override
    public void updateProfile(OnFinishedListener onFinishedListener, String userId, String firstName, String lastName) {
        IProfileApi apiService = ApiClient.getClient().create(IProfileApi.class);
        Call<ApiResponse<String[]>> call = apiService.updateProfileForUser(new UpdateProfile(userId,firstName,lastName));

        call.enqueue(new Callback<ApiResponse<String[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<String[]>> call, Response<ApiResponse<String[]>> response) {
                if (response.isSuccessful()) {
                    onFinishedListener.onFinished();
                } else {
                    onFinishedListener.onFailure("Profile not found");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String[]>> call, Throwable t) {
                onFinishedListener.onFailure("Error on change profile");
            }
        });
    }
}
