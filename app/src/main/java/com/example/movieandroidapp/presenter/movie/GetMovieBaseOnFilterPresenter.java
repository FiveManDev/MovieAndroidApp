package com.example.movieandroidapp.presenter.movie;

import com.example.movieandroidapp.contract.movie.CatalogHomeContract;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.service.movie.GetMovieBaseOnFilterRequest;

import java.util.List;

public class GetMovieBaseOnFilterPresenter implements CatalogHomeContract.Model.OnFinishedListener, CatalogHomeContract.Presenter {
    private CatalogHomeContract.Model model;
    private CatalogHomeContract.View view;

    public GetMovieBaseOnFilterPresenter(CatalogHomeContract.View view) {
        model = new GetMovieBaseOnFilterRequest();
        this.view = view;
    }

    @Override
    public void onFinished(List<Movie> movieArrayList) {
        view.setDataToRecyclerview(movieArrayList);
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailureNew(message);
    }

    @Override
    public void requestDataFromServer(String genreID, String quality, String ratingMin, String ratingMax, String releaseTimeMin, String releaseTimeMax) {
        model.getMovieBaseOnFilter(this, genreID, quality, ratingMin, ratingMax, releaseTimeMin, releaseTimeMax);
    }
}
