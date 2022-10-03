package com.example.movieandroidapp.contract.user;

public interface CreateAccountContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished();
            void onFailure(String message);
        }

        void register(CreateAccountContract.Model.OnFinishedListener onFinishedListener,String userName,String email, String password);
    }
    interface View{
        void onResponseSuccess();
        void onResponseFailure(String message);
    }
    interface Presenter {
        void requestCreateUserToServer(String userName,String email, String password);
    }
}
