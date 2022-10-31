package com.example.movieandroidapp.contract.user;

public interface DeleteUserContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished();
            void onFailure(String message);
        }
        void deleteUserById(DeleteUserContract.Model.OnFinishedListener onFinishedListener,
                              String UserId);
    }
    interface View{
        void onResponseSuccess();
        void onResponseFailure(String message);
    }
    interface Presenter {
        void requestDeleteUser( String UserId);
    }
}
