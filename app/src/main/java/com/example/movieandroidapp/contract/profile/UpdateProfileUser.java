package com.example.movieandroidapp.contract.profile;

import com.example.movieandroidapp.contract.user.ConfirmEmailContract;

public interface UpdateProfileUser {
    interface Model{
        interface OnFinishedListener {
            void onFinished(String code);
            void onFailure(String message);
        }
        void updateProfile(UpdateProfileUser.Model.OnFinishedListener onFinishedListener, String email_address);
    }
    interface View{
        void onResponseSuccess(String code);
        void onResponseFailure(String message);
    }
    interface Presenter {
        void requestUpdateProfile(String userId, String firstname, String lastname);
    }
}
