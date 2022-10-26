package com.example.movieandroidapp.contract.movie;

import com.example.movieandroidapp.model.movie.Movie;

import java.util.List;

public interface GetTotalMovieContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished(int total);
            void onFailure(String message);
        }

        void getTotalMovies(GetTotalMovieContract.Model.OnFinishedListener onFinishedListener);
    }

    interface View{
        void onResponseFailure(String message);
        void onResponseSuccess(int total);
    }

    interface Presenter {
        void requestGetTotalMovies();
    }
}
