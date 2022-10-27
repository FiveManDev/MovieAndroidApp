package com.example.movieandroidapp.contract.movie;

import com.example.movieandroidapp.model.Pagination;
import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.BodyRequest.Filter;

public interface GetMoviesContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished(ResponseFilter<Movie[]> response);
            void onFailure(String message);
        }

        void getMovies(GetMoviesContract.Model.OnFinishedListener onFinishedListener, Filter getMovies);
    }

    interface View{
        void onResponseFailure(String message);
        void onResponseSuccess(ResponseFilter<Movie[]> response);
    }

    interface Presenter {
        void requestGetMovies(Filter getMovies);
    }
}
