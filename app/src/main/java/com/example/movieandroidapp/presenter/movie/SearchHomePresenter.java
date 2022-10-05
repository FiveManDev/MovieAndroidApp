package com.example.movieandroidapp.presenter.movie;

import com.example.movieandroidapp.contract.movie.SearchMovieHome;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.service.movie.GetMoviesBasedOnSearchTextRequest;

import java.util.List;

public class SearchHomePresenter implements SearchMovieHome.Presenter, SearchMovieHome.Model.OnFinishedListener {

    private SearchMovieHome.Model model;
    private SearchMovieHome.View view;

    public SearchHomePresenter(SearchMovieHome.View viewModel) {
        view = viewModel;
        model = new GetMoviesBasedOnSearchTextRequest();
    }


    @Override
    public void requestSearchMovieHome(String query) {
        model.getMoviesBasedOnSearchText(this, query);
    }

    @Override
    public void onFinished(List<Movie> movieArrayList) {
        view.setDataToRecyclerview(movieArrayList);
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }
}
