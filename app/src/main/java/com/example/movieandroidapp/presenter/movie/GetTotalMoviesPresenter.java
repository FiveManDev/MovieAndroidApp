package com.example.movieandroidapp.presenter.movie;

import com.example.movieandroidapp.contract.movie.GetTotalMovieContract;
import com.example.movieandroidapp.service.movie.GetTotalMoviesRequest;

public class GetTotalMoviesPresenter implements GetTotalMovieContract.Presenter,GetTotalMovieContract.Model.OnFinishedListener {
    private GetTotalMovieContract.Model model;
    private GetTotalMovieContract.View viewModel;

    public GetTotalMoviesPresenter(GetTotalMovieContract.View viewModel) {
        model = new GetTotalMoviesRequest();
        this.viewModel = viewModel;
    }


    @Override
    public void onFinished(int total) {
        viewModel.onResponseSuccess(total);
    }

    @Override
    public void onFailure(String message) {
        viewModel.onResponseFailure(message);
    }
    @Override
    public void requestGetTotalMovies() {

        model.getTotalMovies(this);
    }
}
