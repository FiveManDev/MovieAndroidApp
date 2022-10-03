package com.example.movieandroidapp.contract.movie;

import com.example.movieandroidapp.model.movie.Movie;

import java.util.List;

public interface GetTopLastestReleaseMoviesContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished(List<Movie> movieArrayList);
            void onFailure(String message);
        }

        void getTopLastestReleaseMovies(GetTopLastestReleaseMoviesContract.Model.OnFinishedListener onFinishedListener, int top);
    }

    interface View{
        void setDataToRecyclerview(List<Movie> movieListArray);
        void onResponseFailure(String message);
    }

    interface Presenter {
        void requestDataFromServer(int top);
    }

}
