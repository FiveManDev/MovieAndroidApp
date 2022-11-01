package com.example.movieandroidapp.presenter.movie;

import com.example.movieandroidapp.contract.movie.PostMovieContract;
import com.example.movieandroidapp.model.movie.PostMovie;
import com.example.movieandroidapp.service.movie.PostMovieRequest;

public class PostMoviePresenter implements PostMovieContract.Presenter,PostMovieContract.Model.OnFinishedListener {
    PostMovieContract.Model model;
    PostMovieContract.View view;

    public PostMoviePresenter(PostMovieContract.View view) {
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
    public void requestPostMovie(PostMovie postMovie) {
        if(postMovie.getThumbnail()== null){
            view.onResponseFailure("Please choose thumbnail");
        }
        else if(postMovie.getMovieName().isEmpty()){
            view.onResponseFailure("Title cannot be empty");
        }
        else if(postMovie.getDescription().isEmpty()){
            view.onResponseFailure("Description cannot be empty");
        }
        else if(postMovie.getReleaseTime().isEmpty()){
            view.onResponseFailure("Release time cannot be empty");
        }
        else if(postMovie.getRunningTime() == null){
            view.onResponseFailure("Running time cannot be empty");
        }
        else if(postMovie.getAge().isEmpty()){
            view.onResponseFailure("Age cannot be empty");
        }else if(postMovie.getCountry() == null){
            view.onResponseFailure("Country cannot be empty");
        }else if(postMovie.getClassName() == null){
            view.onResponseFailure("Class name cannot be empty");
        }else if(postMovie.getQuality() == null){
            view.onResponseFailure("Quality cannot be empty");
        }else if(postMovie.getGenreNames() == null){
            view.onResponseFailure("Genre cannot be empty");
        }else if(postMovie.getCoverImage()==null){
            view.onResponseFailure("Photo cannot be empty");
        }else if(postMovie.getMovie() == null){
            view.onResponseFailure("Video cannot be empty");
        }
        else{
            model.postMovie(this, postMovie);
        }

    }
}
