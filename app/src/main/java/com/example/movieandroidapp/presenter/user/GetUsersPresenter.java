package com.example.movieandroidapp.presenter.user;

import com.example.movieandroidapp.contract.user.GetUsersContract;
import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.network.BodyRequest.Filter;
import com.example.movieandroidapp.service.User.GetUsersRequest;

public class GetUsersPresenter implements GetUsersContract.Presenter,GetUsersContract.Model.OnFinishedListener {
    GetUsersContract.Model model;
    GetUsersContract.View view;

    public GetUsersPresenter(GetUsersContract.View view) {
        this.view = view;
        model = new GetUsersRequest();
    }

    @Override
    public void onFinished(ResponseFilter<User[]> pagination) {
        view.onResponseSuccess(pagination);
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }

    @Override
    public void requestGetUserToServer(Filter filters) {
        model.getUsers(this,filters);
    }
}
