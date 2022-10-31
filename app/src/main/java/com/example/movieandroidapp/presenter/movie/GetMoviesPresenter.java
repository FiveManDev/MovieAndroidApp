package com.example.movieandroidapp.presenter.movie;

import com.example.movieandroidapp.contract.movie.GetMoviesContract;
import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.BodyRequest.Filter;
import com.example.movieandroidapp.service.movie.GetMoviesRequest;


public class GetMoviesPresenter implements GetMoviesContract.Presenter,GetMoviesContract.Model.OnFinishedListener {
    private GetMoviesContract.Model model;
    private GetMoviesContract.View viewModel;

    public GetMoviesPresenter(GetMoviesContract.View viewModel) {
        model = new GetMoviesRequest();
        this.viewModel = viewModel;
    }


    @Override
    public void onFinished(ResponseFilter<Movie[]> pagination) {
        viewModel.onResponseSuccess(pagination);
    }

    @Override
    public void onFailure(String message) {
        viewModel.onResponseFailure(message);
    }

    @Override
    public void requestGetMovies(Filter getMovies) {
        model.getMovies(this,getMovies);
    }
}
