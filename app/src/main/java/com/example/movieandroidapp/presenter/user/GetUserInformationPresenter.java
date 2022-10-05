package com.example.movieandroidapp.presenter.user;

import com.example.movieandroidapp.contract.user.GetUserInformationContract;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.service.User.GetUserInformationRequest;

public class GetUserInformationPresenter implements GetUserInformationContract.Presenter,GetUserInformationContract.Model.OnFinishedListener {
    private GetUserInformationContract.Model getUserInformationPresenterModel;
    private GetUserInformationContract.View viewModel;

    public GetUserInformationPresenter(GetUserInformationContract.View viewModel) {
        this.viewModel = viewModel;
        getUserInformationPresenterModel = new GetUserInformationRequest();
    }

    @Override
    public void requestGetUserToServer(String id) {
        getUserInformationPresenterModel.getUserInformation(this,id);
    }

    @Override
    public void onFinished(User user) {

        viewModel.onResponseSuccess(user);
    }

    @Override
    public void onFailure(String message) {
        viewModel.onResponseFailure(message);
    }
}
