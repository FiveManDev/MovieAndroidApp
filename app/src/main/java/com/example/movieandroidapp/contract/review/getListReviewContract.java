package com.example.movieandroidapp.contract.review;

import com.example.movieandroidapp.model.Review;

import java.util.List;

public interface getListReviewContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished(List<Review> reviewArrayList);
            void onFailure(String message);
        }

        void GetAllReviewsOfMovie(getListReviewContract.Model.OnFinishedListener onFinishedListener, String idMovie);
    }

    interface View{
        void setDataToRecyclerview(List<Review> reviewListArray);
        void onResponseFailure(String message);
    }

    interface Presenter {
        void requestDataFromServer(String idMovie);
    }

}
