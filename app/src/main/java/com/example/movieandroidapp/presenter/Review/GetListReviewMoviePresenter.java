package com.example.movieandroidapp.presenter.Review;

import com.example.movieandroidapp.contract.review.getListReviewContract;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.service.Review.GetAllReviewsOfMovieRequest;

import java.util.List;

public class GetListReviewMoviePresenter implements getListReviewContract.Presenter,getListReviewContract.Model.OnFinishedListener {
    private getListReviewContract.Model model;
    private getListReviewContract.View view;

    public GetListReviewMoviePresenter(getListReviewContract.View view) {
        this.view = view;
        model = new GetAllReviewsOfMovieRequest();
    }

    @Override
    public void requestDataFromServer(String idMovie) {
        model.GetAllReviewsOfMovie(this,idMovie);
    }

    @Override
    public void onFinished(List<Review> reviewArrayList) {
        view.setDataToRecyclerview(reviewArrayList);
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }
}
