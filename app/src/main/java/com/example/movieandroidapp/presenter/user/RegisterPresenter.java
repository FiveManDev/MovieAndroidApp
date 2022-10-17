package com.example.movieandroidapp.presenter.user;

import com.example.movieandroidapp.contract.user.CreateAccountContract;
import com.example.movieandroidapp.service.User.CreateUserRequest;

public class RegisterPresenter implements CreateAccountContract.Presenter,CreateAccountContract.Model.OnFinishedListener{

    private CreateAccountContract.Model registerModel;
    private CreateAccountContract.View viewModel;

    public RegisterPresenter( CreateAccountContract.View viewModel) {
        this.viewModel = viewModel;
        registerModel = new CreateUserRequest();
    }


    @Override
    public void onFinished() {
        viewModel.onResponseSuccess();
    }

    @Override
    public void onFailure(String message) {
        viewModel.onResponseFailure(message);
    }

    @Override
    public void requestCreateUserToServer(String userName, String email, String password,String code_input,String code) {
        if(!code.equals(code_input)){
            viewModel.onResponseFailure("Code verification not correct");
        }
        else{
            registerModel.register(this,userName,email,password);
        }
    }
}
