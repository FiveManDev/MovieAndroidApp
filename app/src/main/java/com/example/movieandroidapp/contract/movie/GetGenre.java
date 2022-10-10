package com.example.movieandroidapp.contract.movie;

import com.example.movieandroidapp.model.Genre;

import java.util.List;

public interface GetGenre {

    interface Model{
        interface OnFinishedListener {
            void onFinished(List<Genre> genreList);
            void onFailure(String message);
        }

        void getGenre(GetGenre.Model.OnFinishedListener onFinishedListener);
    }

    interface View{
        void onResponseSuccess(List<Genre> genreList);
        void onResponseFailure(String message);
    }

    interface Presenter {
        void requestGetGenres();
    }

}
