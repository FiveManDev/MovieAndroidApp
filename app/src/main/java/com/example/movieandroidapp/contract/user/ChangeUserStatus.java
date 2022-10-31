package com.example.movieandroidapp.contract.user;

public interface ChangeUserStatus {
    interface Model{
        interface OnFinishedListener {
            void onFinished(String userId);
            void onFailure(String message);
        }
        void changeUserStatus(ChangeUserStatus.Model.OnFinishedListener onFinishedListener,
                            String USerId, boolean isBanned);
    }
    interface View{
        void onResponseSuccess(String userID);
        void onResponseFailure(String message);
    }
    interface Presenter {
        void requestChangeStatusUser( String USerId, boolean isBanned);
    }
}
