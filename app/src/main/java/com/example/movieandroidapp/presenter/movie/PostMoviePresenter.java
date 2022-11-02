package com.example.movieandroidapp.presenter.movie;

import com.example.movieandroidapp.contract.movie.PostUpdateMovieContract;
import com.example.movieandroidapp.model.movie.PostMovie;
import com.example.movieandroidapp.service.movie.PostMovieRequest;

public class PostMoviePresenter implements PostUpdateMovieContract.Presenter, PostUpdateMovieContract.Model.OnFinishedListener {
    PostUpdateMovieContract.Model model;
    PostUpdateMovieContract.View view;

    public PostMoviePresenter(PostUpdateMovieContract.View view) {
        this.view = view;
        model = new PostMovieRequest();
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
    public void requestMovie(PostMovie postMovie, String type) {
        if (type.equals("post")) {
            if (postMovie.getCoverImage() == null) {
                view.onResponseFailure("Photo cannot be empty");
            } else if (postMovie.getMovie() == null) {
                view.onResponseFailure("Video cannot be empty");
            } else if (postMovie.getThumbnail() == null) {
                view.onResponseFailure("Please choose thumbnail");
            } else {
                model.postOrUpdateMovie(this, postMovie, type);
            }
        } else if (postMovie.getDescription().isEmpty()) {
            view.onResponseFailure("Description cannot be empty");
        } else if (postMovie.getReleaseTime().isEmpty()) {
            view.onResponseFailure("Release time cannot be empty");
        } else if (postMovie.getRunningTime() == null) {
            view.onResponseFailure("Running time cannot be empty");
        } else if (postMovie.getAge().isEmpty()) {
            view.onResponseFailure("Age cannot be empty");
        } else if (postMovie.getCountry() == null) {
            view.onResponseFailure("Country cannot be empty");
        } else if (postMovie.getClassName() == null) {
            view.onResponseFailure("Class name cannot be empty");
        } else if (postMovie.getQuality() == null) {
            view.onResponseFailure("Quality cannot be empty");
        } else if (postMovie.getGenreNames() == null) {
            view.onResponseFailure("Genre cannot be empty");
        } else if (postMovie.getMovieName().isEmpty()) {
            view.onResponseFailure("Title cannot be empty");
        } else {
            model.postOrUpdateMovie(this, postMovie, type);
        }
    }
}
