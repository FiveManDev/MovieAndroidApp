package com.example.movieandroidapp.contract.user;

public interface ResetPasswordContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished();
            void onFailure(String message);
        }
        void resetPassword(ResetPasswordContract.Model.OnFinishedListener onFinishedListener
                ,String newPassword, String confirmPassword, String email);
    }
    interface View{
        void onResponseSuccess();
        void onResponseFailure(String message);
    }
    interface Presenter {
        void confirmEmailToServer(String newPassword, String confirmPassword, String email);
    }
}
