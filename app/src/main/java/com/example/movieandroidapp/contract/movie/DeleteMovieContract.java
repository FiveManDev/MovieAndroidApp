package com.example.movieandroidapp.contract.movie;

public interface DeleteMovieContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished();
            void onFailure(String message);
        }

        void deleteMovieById(DeleteMovieContract.Model.OnFinishedListener onFinishedListener,String id);
    }

    interface View{
        void onResponseFailure(String message);
        void onResponseSuccess();
    }

    interface Presenter {
        void requestDeleteMovies(String id);
    }
}
