package com.example.movieandroidapp.presenter.movie;

import com.example.movieandroidapp.contract.movie.GetMoviesBasedOnGenreContract;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.service.movie.GetMoviesBasedOnGenreRequest;

import java.util.List;

public class GetTopLastestPublicationMoviesPresenter implements GetMoviesBasedOnGenreContract.Presenter, GetMoviesBasedOnGenreContract.Model.OnFinishedListener {

    private GetMoviesBasedOnGenreContract.Model getTopLastestPublicationMoviesPresenter;
    private GetMoviesBasedOnGenreContract.View viewModel;

    public GetTopLastestPublicationMoviesPresenter(GetMoviesBasedOnGenreContract.View viewModel) {
        getTopLastestPublicationMoviesPresenter = new GetMoviesBasedOnGenreRequest();
        this.viewModel = viewModel;
    }

    @Override
    public void requestDataFromServerNew(String genre, int top) {
        getTopLastestPublicationMoviesPresenter.getMoviesBasedOnGenre(this ,genre, top);
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
