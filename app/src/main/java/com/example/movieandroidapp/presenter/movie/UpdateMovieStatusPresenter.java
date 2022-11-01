package com.example.movieandroidapp.presenter.movie;

import com.example.movieandroidapp.contract.movie.ChangeStatusMovieContract;
import com.example.movieandroidapp.network.BodyRequest.ChangeStatusBody;
import com.example.movieandroidapp.service.movie.UpdateMovieStatusRequest;

public class UpdateMovieStatusPresenter implements ChangeStatusMovieContract.Presenter, ChangeStatusMovieContract.Model.OnFinishedListener {
    ChangeStatusMovieContract.Model model;
    ChangeStatusMovieContract.View view;

    public UpdateMovieStatusPresenter(ChangeStatusMovieContract.View view) {
        this.view = view;
        model = new UpdateMovieStatusRequest();
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
    public void requestUpdateStatus(ChangeStatusBody statusBody) {
        model.changeStatusMovie(this,statusBody);
    }
}
