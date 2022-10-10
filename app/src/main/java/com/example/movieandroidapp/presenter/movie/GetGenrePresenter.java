package com.example.movieandroidapp.presenter.movie;

import com.example.movieandroidapp.contract.movie.GetGenre;
import com.example.movieandroidapp.model.Genre;
import com.example.movieandroidapp.service.movie.GetAllGenreOfMovieRequest;

import java.util.List;

public class GetGenrePresenter implements GetGenre.Presenter, GetGenre.Model.OnFinishedListener {

    private GetGenre.View view;
    private GetGenre.Model model;

    public GetGenrePresenter(GetGenre.View view) {
        this.view = view;
        model = new GetAllGenreOfMovieRequest();
    }

    @Override
    public void requestGetGenres() {
        model.getGenre(this);
    }

    @Override
    public void onFinished(List<Genre> genreList) {
        view.onResponseSuccess(genreList);
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }
}
