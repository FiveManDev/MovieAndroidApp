package com.example.movieandroidapp.contract;

public interface GetTotalContract {
    interface Model{
        interface OnFinishedListener {
            void onFinished(int total);
            void onFailure(String message);
        }

        void getTotal(GetTotalContract.Model.OnFinishedListener onFinishedListener);
    }

    interface View{
        void onResponseFailure(String message);
        void onResponseSuccess(int total);
    }

    interface Presenter {
        void requestGetTotal();
    }
}
