package com.example.movieandroidapp.contract.user;

import com.example.movieandroidapp.model.User;

public interface GetUserInformationContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished(User user);
            void onFailure(String message);
        }

        void getUserInformation(GetUserInformationContract.Model.OnFinishedListener onFinishedListener);
    }
    interface View{
        void onResponseSuccess(User user);
        void onResponseFailure(String message);
    }
    interface Presenter {
        void requestGetUserToServer();
    }
}
