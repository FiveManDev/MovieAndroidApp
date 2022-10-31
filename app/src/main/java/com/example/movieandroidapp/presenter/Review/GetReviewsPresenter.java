package com.example.movieandroidapp.presenter.Review;

import com.example.movieandroidapp.contract.review.GetReviews;
import com.example.movieandroidapp.service.Review.GetReviewsRequest;
import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.network.BodyRequest.Filter;


public class GetReviewsPresenter implements GetReviews.Presenter, GetReviews.Model.OnFinishedListener {

    GetReviews.Model model;
    GetReviews.View view;

    public GetReviewsPresenter(GetReviews.View view) {
        this.view = view;
        model = new GetReviewsRequest();
    }


    @Override
    public void onFinished(ResponseFilter<Review[]> response) {
        view.onResponseSuccess(response);
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }


    @Override
    public void requestGetReviews(Filter filter) {
        model.getReviews(this,filter);
    }
}
