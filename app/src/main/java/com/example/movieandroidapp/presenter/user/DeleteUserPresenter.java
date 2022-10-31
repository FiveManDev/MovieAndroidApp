package com.example.movieandroidapp.presenter.user;

import com.example.movieandroidapp.contract.user.DeleteUserContract;
import com.example.movieandroidapp.service.User.DeleteUserRequest;

public class DeleteUserPresenter implements DeleteUserContract.Presenter,DeleteUserContract.Model.OnFinishedListener {
    DeleteUserContract.Model model;
    DeleteUserContract.View view;

    public DeleteUserPresenter(DeleteUserContract.View view) {
        this.view = view;
        model = new DeleteUserRequest();
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
    public void requestDeleteUser(String UserId) {
        model.deleteUserById(this,UserId);
    }
}
