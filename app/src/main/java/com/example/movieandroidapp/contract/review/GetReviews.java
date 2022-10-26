package com.example.movieandroidapp.contract.review;

import com.example.movieandroidapp.contract.movie.GetMoviesContract;
import com.example.movieandroidapp.model.Pagination;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.BodyRequest.Filter;

public interface GetReviews {
    interface Model{
        interface OnFinishedListener {
            void onFinished(Pagination<Review[]> pagination);
            void onFailure(String message);
        }

        void getReviews(GetReviews.Model.OnFinishedListener onFinishedListener, Filter filter);
    }

    interface View{
        void onResponseSuccess(Pagination<Review[]> pagination);
        void onResponseFailure(String message);
    }

    interface Presenter {
        void requestGetReviews(Filter filter);
    }
}
