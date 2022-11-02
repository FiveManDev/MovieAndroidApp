package com.example.movieandroidapp.contract.movie;

import com.example.movieandroidapp.model.movie.PostMovie;

public interface PostUpdateMovieContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished();
            void onFailure(String message);
        }

        void postOrUpdateMovie(PostUpdateMovieContract.Model.OnFinishedListener onFinishedListener, PostMovie postMovie, String type);

    }

    interface View{
        void onResponseSuccess();
        void onResponseFailure(String message);
    }

    interface Presenter {
        void requestMovie(PostMovie postMovie, String type);
    }
}
