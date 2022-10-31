package com.example.movieandroidapp.presenter.Review;

import com.example.movieandroidapp.contract.review.DeleteReviewContract;
import com.example.movieandroidapp.service.Review.DeleteReviewRequest;

public class DeleteReviewPresenter implements DeleteReviewContract.Model.OnFinishedListener,DeleteReviewContract.Presenter {
    DeleteReviewContract.Model model;
    DeleteReviewContract.View view;

    public DeleteReviewPresenter(DeleteReviewContract.View view) {
        model = new DeleteReviewRequest();
        this.view = view;
    }

    @Override
    public void onFinished() {
        view.onResponseSuccess();
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }

    @Override
    public void requestGetReviews(String reviewId) {
        model.deleteReview(this,reviewId);
    }
}
