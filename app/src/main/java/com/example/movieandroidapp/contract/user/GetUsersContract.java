package com.example.movieandroidapp.contract.user;

import com.example.movieandroidapp.model.Pagination;
import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.BodyRequest.Filter;

public interface GetUsersContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished(ResponseFilter<User[]> pagination);
            void onFailure(String message);
        }

        void getUsers(GetUsersContract.Model.OnFinishedListener onFinishedListener, Filter filters);
    }
    interface View{
        void onResponseSuccess(ResponseFilter<User[]> pagination);
        void onResponseFailure(String message);
    }
    interface Presenter {
        void requestGetUserToServer(Filter filters);
    }
}
