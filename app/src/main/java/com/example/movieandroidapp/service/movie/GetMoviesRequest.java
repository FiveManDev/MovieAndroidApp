package com.example.movieandroidapp.service.movie;

import com.example.movieandroidapp.contract.movie.GetMoviesContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.Pagination;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.BodyRequest.Filter;
import com.example.movieandroidapp.network.Movie.IMovieApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMoviesRequest implements GetMoviesContract.Model {
    @Override
    public void getMovies(OnFinishedListener onFinishedListener, Filter getMovies) {
        IMovieApi apiService = ApiClient.getClient().create(IMovieApi.class);
        Call<ApiResponse<Pagination<Movie[]>>> call =
                apiService.GetMovies(
                        getMovies.getPageIndex(),
                        getMovies.getPageSize(),
                        getMovies.getQuery(),
                        getMovies.getSortBy(),
                        getMovies.getSortType());
        call.enqueue(new Callback<ApiResponse<Pagination<Movie[]>>>() {
            @Override
            public void onResponse(Call<ApiResponse<Pagination<Movie[]>>> call, Response<ApiResponse<Pagination<Movie[]>>> response) {
                if (response.isSuccessful()) {
                        onFinishedListener.onFinished(response.body().getData());
                } else {
                    onFinishedListener.onFailure("Don't have any movie");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Pagination<Movie[]>>> call, Throwable t) {
                onFinishedListener.onFailure("Error in Get movies");
            }
        });
    }
}
