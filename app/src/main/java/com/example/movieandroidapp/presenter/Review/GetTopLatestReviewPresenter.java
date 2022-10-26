package com.example.movieandroidapp.presenter.Review;

import com.example.movieandroidapp.contract.review.GetTopLatestReviewContract;
import com.example.movieandroidapp.model.Review;

import java.util.List;

public class GetTopLatestReviewPresenter implements GetTopLatestReviewContract.Presenter, GetTopLatestReviewContract.Model.OnFinishedListener {

    GetTopLatestReviewContract.Model model;
    GetTopLatestReviewContract.View view;

    public GetTopLatestReviewPresenter(GetTopLatestReviewContract.View view) {
        this.view = view;
        model = new com.example.movieandroidapp.service.Review.GetTopLatestReviewRequest();
    }

    @Override
    public void onFinished(List<Review> reviewArrayList) {
        view.setDataToRecyclerview(reviewArrayList);
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }

    @Override
    public void requestDataFromServer(String top) {
        model.getTopLatestReview(this,top);
    }
}
