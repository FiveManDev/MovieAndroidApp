package com.example.movieandroidapp.presenter.user;

import com.example.movieandroidapp.contract.user.ChangePasswordContract;
import com.example.movieandroidapp.service.User.ChangePasswordRequest;

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter,ChangePasswordContract.Model.OnFinishedListener {
    private ChangePasswordContract.Model model;
    private ChangePasswordContract.View view;

    public ChangePasswordPresenter(ChangePasswordContract.View view) {
        this.view = view;
        model = new ChangePasswordRequest();
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
    public void requestChangePassword(String oldPassword, String newPassword, String confirmPassword) {
        if(oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()){
            view.onResponseFailure("Field must not be empty");
        }
        else if(newPassword.length() < 6){
            view.onResponseFailure("New password must be greater than 6 character");
        }
        else if(!newPassword.equals(confirmPassword)){
            view.onResponseFailure("Confirm password not match");
        }
        else{
            model.changePassword(this,oldPassword,newPassword,confirmPassword);
        }
    }
}
