package com.example.movieandroidapp.contract.user;

public interface ConfirmEmailContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished(String code);
            void onFailure(String message);
        }
        void confirmEmail(ConfirmEmailContract.Model.OnFinishedListener onFinishedListener,String email_address);
    }
    interface View{
        void onResponseSuccess(String code);
        void onResponseFailure(String message);
    }
    interface Presenter {
        void confirmEmailToServer(String userName, String email, String password);
    }
}
