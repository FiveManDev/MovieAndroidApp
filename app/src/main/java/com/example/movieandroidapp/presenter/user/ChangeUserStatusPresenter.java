package com.example.movieandroidapp.presenter.user;

import com.example.movieandroidapp.contract.user.ChangeUserStatus;
import com.example.movieandroidapp.service.User.ChangeUserStatusRequest;

public class ChangeUserStatusPresenter implements ChangeUserStatus.Presenter,ChangeUserStatus.Model.OnFinishedListener {

    ChangeUserStatus.Model model;
    ChangeUserStatus.View view;

    public ChangeUserStatusPresenter(ChangeUserStatus.View view) {
        this.view = view;
        model = new ChangeUserStatusRequest();
    }

    @Override
    public void onFinished(String userID) {
        view.onResponseSuccess(userID);
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }

    @Override
    public void requestChangeStatusUser(String USerId, boolean isBanned) {
        model.changeUserStatus(this, USerId, isBanned);
    }
}
