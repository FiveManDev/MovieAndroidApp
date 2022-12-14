package com.example.movieandroidapp.presenter.Review;

import com.example.movieandroidapp.contract.review.CreateReviewContract;
import com.example.movieandroidapp.network.BodyRequest.ReviewBody;
import com.example.movieandroidapp.service.Review.CreateReviewRequest;

public class CreateReviewMoviePresenter implements CreateReviewContract.Presenter,CreateReviewContract.Model.OnFinishedListener {
    private CreateReviewContract.Model model;
    private CreateReviewContract.View view;

    public CreateReviewMoviePresenter(CreateReviewContract.View view) {
        this.view = view;
        model = new CreateReviewRequest();
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
    public void requestCreateReview(ReviewBody review) {
        if(review.getTitle().isEmpty() || review.getTitle() == null){
            view.onResponseFailure("Title can not be empty");
        }
        else if(review.getReviewContent().isEmpty() || review.getReviewContent() == null){
            view.onResponseFailure("Content can not be empty");
        }
        else{
            model.createReview(this,review);
        }
    }
}
