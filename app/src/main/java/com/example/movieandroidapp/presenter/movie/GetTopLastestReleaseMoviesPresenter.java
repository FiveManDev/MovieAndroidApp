package com.example.movieandroidapp.presenter.movie;

import com.example.movieandroidapp.contract.movie.GetTopLastestReleaseMoviesContract;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.service.movie.GetTopLastestReleaseMoviesRequest;

import java.util.List;

public class GetTopLastestReleaseMoviesPresenter implements GetTopLastestReleaseMoviesContract.Presenter,GetTopLastestReleaseMoviesContract.Model.OnFinishedListener {

    private GetTopLastestReleaseMoviesContract.Model getTopLastestReleaseMoviesPresenter;
    private GetTopLastestReleaseMoviesContract.View viewModel;

    public GetTopLastestReleaseMoviesPresenter(GetTopLastestReleaseMoviesContract.View viewModel) {
        getTopLastestReleaseMoviesPresenter = new GetTopLastestReleaseMoviesRequest();
        this.viewModel = viewModel;
    }

    @Override
    public void requestDataFromServer(int top) {
        getTopLastestReleaseMoviesPresenter.getTopLastestReleaseMovies(this ,top);
    }

    @Override
    public void onFinished(List<Movie> movieArrayList) {
        viewModel.setDataToRecyclerview(movieArrayList);
    }

    @Override
    public void onFailure(String message) {
        viewModel.onResponseFailure(message);
    }
}
