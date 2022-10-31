package com.example.movieandroidapp.contract.review;


public interface DeleteReviewContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished();
            void onFailure(String message);
        }

        void deleteReview(DeleteReviewContract.Model.OnFinishedListener onFinishedListener, String reviewId);
    }

    interface View{
        void onResponseSuccess();
        void onResponseFailure(String message);
    }

    interface Presenter {
        void requestGetReviews(String reviewId);
    }
}
