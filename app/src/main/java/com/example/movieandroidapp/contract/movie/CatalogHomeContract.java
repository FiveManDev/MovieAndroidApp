package com.example.movieandroidapp.contract.movie;

import com.example.movieandroidapp.model.movie.Movie;

import java.util.List;

public interface CatalogHomeContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished(List<Movie> movieArrayList);
            void onFailure(String message);
        }

        void getMovieBaseOnFilter(CatalogHomeContract.Model.OnFinishedListener onFinishedListener,
                                  String genreID,String quality, String ratingMin, String ratingMax,
                                  String releaseTimeMin, String releaseTimeMax
                                  );
    }

    interface View{
        void setDataToRecyclerview(List<Movie> movieListArray);
        void onResponseFailureNew(String message);
    }

    interface Presenter {
        void requestDataFromServer(String genreID,String quality, String ratingMin, String ratingMax,
                                   String releaseTimeMin, String releaseTimeMax);
    }
}
