package com.example.movieandroidapp.presenter.user;

import com.example.movieandroidapp.contract.user.LoginContract;
import com.example.movieandroidapp.model.TokenModel;
import com.example.movieandroidapp.service.User.LoginRequest;

public class LoginPresenter implements LoginContract.Presenter,LoginContract.Model.OnFinishedListener {
    private LoginContract.Model loginModel;
    private LoginContract.View viewModel;

    public LoginPresenter( LoginContract.View viewModel) {
        this.viewModel = viewModel;
        loginModel = new LoginRequest();
    }

    @Override
    public void requestLoginToServer(String userName,String password) {
        loginModel.login(this,userName,password);
    }

    @Override
    public void onFinished(TokenModel tokenModel) {
        viewModel.onResponseSuccess(tokenModel);
    }

    @Override
    public void onFailure(String message) {
        viewModel.onResponseFailure(message);
    }
}
