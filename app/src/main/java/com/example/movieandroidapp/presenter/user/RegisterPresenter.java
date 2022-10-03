package com.example.movieandroidapp.presenter.user;

import com.example.movieandroidapp.Utility.Extension;
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
    public void requestCreateUserToServer(String userName, String email, String password) {
        if(userName.length() == 0 ){
            viewModel.onResponseFailure("User name can not be empty");
        }
        else if(password.length() == 0 ){
            viewModel.onResponseFailure("Password can not be empty");
        }
        else if(email.length() == 0 ){
            viewModel.onResponseFailure("Email can not be empty");
        }
        else if(!Extension.isLength(userName)){
            viewModel.onResponseFailure("User name must be greater than 6 character");
        }
        else if(!Extension.isEmail(email)){
            viewModel.onResponseFailure("Please enter a valid email address");
        }
        else if(!Extension.isLength(password)){
            viewModel.onResponseFailure("Password must be greater than 6 character");
        }
        else{
            registerModel.register(this,userName,email,password);
        }

    }
}
