package com.example.movieandroidapp.presenter.user;

import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.contract.user.ForgotPasswordContract;
import com.example.movieandroidapp.service.User.ConfirmEmailForgotRequest;

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter,ForgotPasswordContract.Model.OnFinishedListener {

    ForgotPasswordContract.View view;
    ForgotPasswordContract.Model model;

    public ForgotPasswordPresenter(ForgotPasswordContract.View view) {
        this.view = view;
        model = new ConfirmEmailForgotRequest();
    }

    @Override
    public void confirmEmailToServer(String email) {
        if(Extension.isEmail(email)){
            model.confirmEmail(this, email);
        }
        else{
            view.onResponseFailure("Please enter a valid email address");
        }
    }

    @Override
    public void onFinished(String code) {
        view.onResponseSuccess(code);
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }
}
