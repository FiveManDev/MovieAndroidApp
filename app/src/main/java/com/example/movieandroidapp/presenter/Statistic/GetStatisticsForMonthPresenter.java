package com.example.movieandroidapp.presenter.Statistic;

import com.example.movieandroidapp.contract.StatisticsContract;
import com.example.movieandroidapp.model.Statistic;
import com.example.movieandroidapp.service.Statistic.GetStatisticsForMonthRequest;

public class GetStatisticsForMonthPresenter implements StatisticsContract.Presenter,StatisticsContract.Model.OnFinishedListener {
    private StatisticsContract.Model model;
    private StatisticsContract.View view;

    public GetStatisticsForMonthPresenter(StatisticsContract.View view) {
        this.view = view;
        model = new GetStatisticsForMonthRequest();
    }

    @Override
    public void onFinished(Statistic statistic) {
        view.onResponseSuccess(statistic);
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }

    @Override
    public void requestGetGenres(String monthAndYear) {
        model.getStatistics(this,monthAndYear);
    }
}
