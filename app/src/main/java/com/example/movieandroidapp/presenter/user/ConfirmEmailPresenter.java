package com.example.movieandroidapp.presenter.user;

import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.contract.user.ConfirmEmailContract;
import com.example.movieandroidapp.service.User.ConfirmEmail;

public class ConfirmEmailPresenter implements ConfirmEmailContract.Presenter, ConfirmEmailContract.Model.OnFinishedListener {
    private ConfirmEmailContract.Model model;
    private ConfirmEmailContract.View view;

    public ConfirmEmailPresenter(ConfirmEmailContract.View view) {
        this.view = view;
        model = new ConfirmEmail();
    }

    @Override
    public void onFinished(String code) {
        view.onResponseSuccess(code);
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }

    @Override
    public void confirmEmailToServer(String userName, String email, String password) {
        if (userName.length() == 0) {
            view.onResponseFailure("User name can not be empty");
        } else if (password.length() == 0) {
            view.onResponseFailure("Password can not be empty");
        } else if (email.length() == 0) {
            view.onResponseFailure("Email can not be empty");
        } else if (!Extension.isLength(userName)) {
            view.onResponseFailure("User name must be greater than 6 character");
        } else if (!Extension.isEmail(email)) {
            view.onResponseFailure("Please enter a valid email address");
        } else if (!Extension.isLength(password)) {
            view.onResponseFailure("Password must be greater than 6 character");
        } else {
            model.confirmEmail(this, email);
        }
    }
}
