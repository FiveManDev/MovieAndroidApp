package com.example.movieandroidapp.network.Movie;

import com.example.movieandroidapp.model.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMovieApi {

    //Thư mục khai báo các phương thức get,post,put,patch
    @GET("movie/popular")
    Call<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int pageNo);
}
