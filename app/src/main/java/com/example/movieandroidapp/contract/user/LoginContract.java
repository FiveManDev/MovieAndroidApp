package com.example.movieandroidapp.contract.user;

import com.example.movieandroidapp.model.TokenModel;

public interface LoginContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished(TokenModel tokenModel);
            void onFailure(String message);
        }
        void login(LoginContract.Model.OnFinishedListener onFinishedListener,String userName,String password);
    }
    interface View{
        void onResponseSuccess(TokenModel tokenModel);
        void onResponseFailure(String message);
    }
    interface Presenter {
        void requestLoginToServer(String userName,String password);
    }
}
