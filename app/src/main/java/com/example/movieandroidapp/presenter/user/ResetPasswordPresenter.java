package com.example.movieandroidapp.presenter.user;

import com.example.movieandroidapp.contract.user.ResetPasswordContract;
import com.example.movieandroidapp.service.User.ResetPasswordRequest;

public class ResetPasswordPresenter implements ResetPasswordContract.Presenter, ResetPasswordContract.Model.OnFinishedListener {
    private ResetPasswordContract.Model model;
    private ResetPasswordContract.View view;

    public ResetPasswordPresenter(ResetPasswordContract.View view) {
        this.view = view;
        model = new ResetPasswordRequest();
    }

    @Override
    public void onFinished() {
        view.onResponseSuccess();
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }

    @Override
    public void confirmEmailToServer(String newPassword, String confirmPassword, String email) {
        if (newPassword.length() == 0) {
            view.onResponseFailure("New password can not be empty");
        } else if (confirmPassword.length() == 0) {
            view.onResponseFailure("Confirm password can not be empty");
        }else if (!newPassword.equals(confirmPassword)) {
            view.onResponseFailure("Confirm password not match");
        } else {
            model.resetPassword(this,newPassword, confirmPassword, email);
        }
//them truong hop 6 ky tu
    }
}
