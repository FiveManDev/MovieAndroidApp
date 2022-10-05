package com.example.movieandroidapp.contract.movie;

import com.example.movieandroidapp.model.movie.Movie;

import java.util.List;

public interface SearchMovieHome {

    interface Model{
        interface OnFinishedListener {
            void onFinished(List<Movie> movieArrayList);
            void onFailure(String message);
        }

        void getMoviesBasedOnSearchText(SearchMovieHome.Model.OnFinishedListener onFinishedListener, String query);
    }

    interface View{
        void setDataToRecyclerview(List<Movie> movieListArray);
        void onResponseFailure(String message);
    }

    interface Presenter {
        void requestSearchMovieHome(String query);
    }

}
