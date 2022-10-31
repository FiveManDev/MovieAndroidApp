package com.example.movieandroidapp.contract.review;

import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.network.BodyRequest.Filter;

public interface GetReviews {
    interface Model{
        interface OnFinishedListener {
            void onFinished(ResponseFilter<Review[]> response);
            void onFailure(String message);
        }

        void getReviews(GetReviews.Model.OnFinishedListener onFinishedListener, Filter filter);
    }

    interface View{
        void onResponseSuccess(ResponseFilter<Review[]> response);
        void onResponseFailure(String message);
    }

    interface Presenter {
        void requestGetReviews(Filter filter);
    }
}
