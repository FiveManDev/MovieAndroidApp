package com.example.movieandroidapp.contract.movie;

import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.model.movie.PostMovie;

import java.util.List;

public interface PostMovieContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished();
            void onFailure(String message);
        }

        void postMovie(PostMovieContract.Model.OnFinishedListener onFinishedListener, PostMovie postMovie);
    }

    interface View{
        void onResponseSuccess();
        void onResponseFailure(String message);
    }

    interface Presenter {
        void requestPostMovie(PostMovie postMovie);
    }
}
