package com.example.movieandroidapp.contract;
import com.example.movieandroidapp.model.Statistic;


public interface StatisticsContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished(Statistic statistic);
            void onFailure(String message);
        }

        void getStatistics(StatisticsContract.Model.OnFinishedListener onFinishedListener,String monthAndYear);
    }

    interface View{
        void onResponseSuccess(Statistic statistic);
        void onResponseFailure(String message);
    }

    interface Presenter {
        void requestGetGenres(String monthAndYear);
    }
}
