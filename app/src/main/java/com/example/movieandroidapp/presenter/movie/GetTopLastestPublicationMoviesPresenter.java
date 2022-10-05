package com.example.movieandroidapp.presenter.movie;

import com.example.movieandroidapp.contract.movie.GetTopLastestPublicationMoviesContract;
import com.example.movieandroidapp.contract.movie.GetTopLastestReleaseMoviesContract;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.service.movie.GetTopLastestPublicationMoviesRequest;
import com.example.movieandroidapp.service.movie.GetTopLastestReleaseMoviesRequest;

import java.util.List;

public class GetTopLastestPublicationMoviesPresenter implements GetTopLastestPublicationMoviesContract.Presenter,GetTopLastestPublicationMoviesContract.Model.OnFinishedListener {

    private GetTopLastestPublicationMoviesContract.Model getTopLastestPublicationMoviesPresenter;
    private GetTopLastestPublicationMoviesContract.View viewModel;

    public GetTopLastestPublicationMoviesPresenter(GetTopLastestPublicationMoviesContract.View viewModel) {
        getTopLastestPublicationMoviesPresenter = new GetTopLastestPublicationMoviesRequest();
        this.viewModel = viewModel;
    }

    @Override
    public void requestDataFromServerNew(int top) {
        getTopLastestPublicationMoviesPresenter.getTopLastestPublicationMovies(this ,top);
    }

    @Override
    public void onFinished(List<Movie> movieArrayList) {
        viewModel.setDataToRecyclerviewNew(movieArrayList);
    }

    @Override
    public void onFailure(String message) {
        viewModel.onResponseFailureNew(message);
    }
}
