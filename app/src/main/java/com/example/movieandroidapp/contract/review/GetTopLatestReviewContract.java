package com.example.movieandroidapp.contract.review;

import com.example.movieandroidapp.model.Review;

import java.util.List;

public interface GetTopLatestReviewContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished(List<Review> reviewArrayList);
            void onFailure(String message);
        }

        void getTopLatestReview(GetTopLatestReviewContract.Model.OnFinishedListener onFinishedListener, String top);
    }

    interface View{
        void setDataToRecyclerview(List<Review> reviewListArray);
        void onResponseFailure(String message);
    }

    interface Presenter {
        void requestDataFromServer(String top);
    }
}
