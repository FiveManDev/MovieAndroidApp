package com.example.movieandroidapp.presenter.Statistic;

import com.example.movieandroidapp.contract.GetTotalContract;
import com.example.movieandroidapp.service.movie.GetTotalMoviesRequest;

public class GetTotalPresenter implements GetTotalContract.Presenter, GetTotalContract.Model.OnFinishedListener {
    private GetTotalContract.Model model;
    private GetTotalContract.View viewModel;

    public GetTotalPresenter(GetTotalContract.View viewModel,GetTotalContract.Model model) {
        this.model = model;
        this.viewModel = viewModel;
    }


    @Override
    public void onFinished(int total) {
        viewModel.onResponseSuccess(total);
    }

    @Override
    public void onFailure(String message) {
        viewModel.onResponseFailure(message);
    }
    @Override
    public void requestGetTotal() {
        model.getTotal(this);
    }
}
