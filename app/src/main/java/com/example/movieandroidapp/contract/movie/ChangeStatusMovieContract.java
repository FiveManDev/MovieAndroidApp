package com.example.movieandroidapp.contract.movie;

import com.example.movieandroidapp.network.BodyRequest.ChangeStatusBody;

public interface ChangeStatusMovieContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished();
            void onFailure(String message);
        }

        void changeStatusMovie(ChangeStatusMovieContract.Model.OnFinishedListener onFinishedListener, ChangeStatusBody statusBody);
    }

    interface View{
        void onResponseFailure(String message);
        void onResponseSuccess();
    }

    interface Presenter {
        void requestUpdateStatus(ChangeStatusBody statusBody);
    }
}
