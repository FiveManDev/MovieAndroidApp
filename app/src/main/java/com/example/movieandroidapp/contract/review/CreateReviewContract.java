package com.example.movieandroidapp.contract.review;

import com.example.movieandroidapp.network.BodyRequest.ReviewBody;


public interface CreateReviewContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished();
            void onFailure(String message);
        }
        void createReview(CreateReviewContract.Model.OnFinishedListener onFinishedListener, ReviewBody review);
    }
    interface View{
        void onResponseSuccess();
        void onResponseFailure(String message);
    }
    interface Presenter {
        void requestCreateReview(ReviewBody review);
    }
}
