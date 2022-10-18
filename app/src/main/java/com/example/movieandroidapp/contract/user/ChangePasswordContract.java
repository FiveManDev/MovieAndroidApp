package com.example.movieandroidapp.contract.user;

import com.example.movieandroidapp.contract.profile.UpdateProfileUser;

public interface ChangePasswordContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished();
            void onFailure(String message);
        }
        void changePassword(ChangePasswordContract.Model.OnFinishedListener onFinishedListener,
                            String oldPassword, String newPassword, String confirmPassword);
    }
    interface View{
        void onResponseSuccess();
        void onResponseFailure(String message);
    }
    interface Presenter {
        void requestChangePassword(String oldPassword, String newPassword, String confirmPassword);
    }
}
