package com.example.movieandroidapp.presenter;

import com.example.movieandroidapp.contract.MovieListContract;
import com.example.movieandroidapp.model.Movie;
import com.example.movieandroidapp.service.MovieListModel;


import java.util.List;

public class MoviePresenter implements MovieListContract.Presenter, MovieListContract.Model.OnFinishedListener{

    private MovieListContract.View movieListView;
    private MovieListContract.Model movieListModel;

    public MoviePresenter(MovieListContract.View movieListView) {
        this.movieListView = movieListView;
        movieListModel = new MovieListModel();
    }

    @Override
    public void onFinished(List<Movie> movieArrayList) {
        movieListView.setDataToRecyclerview(movieArrayList);

        if(movieListView != null) {
            movieListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        movieListView.onResponseFailure(t);

        if(movieListView != null) {
            movieListView.hideProgress();
        }
    }

    @Override
    public void requestDataFromServer() {
        if(movieListView != null) {
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this, 1);
    }
}
