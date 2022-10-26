package com.example.movieandroidapp.presenter.movie;

import com.example.movieandroidapp.contract.movie.DeleteMovieContract;
import com.example.movieandroidapp.service.movie.DeleteMovieRequest;

public class DeleteMoviePresenter implements DeleteMovieContract.Presenter,DeleteMovieContract.Model.OnFinishedListener {
    DeleteMovieContract.Model model;
    DeleteMovieContract.View view;

    public DeleteMoviePresenter(DeleteMovieContract.View view) {
        model = new DeleteMovieRequest();
        this.view = view;
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
    public void requestDeleteMovies(String id) {
        model.deleteMovieById(this,id);
    }
}
