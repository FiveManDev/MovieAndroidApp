package com.example.movieandroidapp.contract.movie;

import com.example.movieandroidapp.model.movie.Movie;

import java.util.List;

public interface GetTopLastestPublicationMoviesContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished(List<Movie> movieArrayList);
            void onFailure(String message);
        }

        void getTopLastestPublicationMovies(GetTopLastestPublicationMoviesContract.Model.OnFinishedListener onFinishedListener, int top);
    }

    interface View{
        void setDataToRecyclerviewNew(List<Movie> movieListArray);
        void onResponseFailureNew(String message);
    }

    interface Presenter {
        void requestDataFromServerNew(int top);
    }

}
