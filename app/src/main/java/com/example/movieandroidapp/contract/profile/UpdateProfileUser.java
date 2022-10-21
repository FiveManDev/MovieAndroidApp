package com.example.movieandroidapp.contract.profile;

import com.example.movieandroidapp.contract.user.ConfirmEmailContract;

public interface UpdateProfileUser {
    interface Model{
        interface OnFinishedListener {
            void onFinished();
            void onFailure(String message);
        }
        void updateProfile(UpdateProfileUser.Model.OnFinishedListener onFinishedListener, String userId,
                           String firstName,
                           String lastName
                           );
    }
    interface View{
        void onResponseSuccess();
        void onResponseFailure(String message);
    }
    interface Presenter {
        void requestUpdateProfile(String userId, String firstname, String lastname);
    }
}
