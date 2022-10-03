package com.example.movieandroidapp.network.Movie;

import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.MovieListResponse;
import com.example.movieandroidapp.model.movie.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMovieApi {

    //Thư mục khai báo các phương thức get,post,put,patch
    @GET(MovieApiUrl.Login)
    Call<MovieListResponse> getPopularMovies(@Query("page") int pageNo);

    @GET(MovieApiUrl.GetTopLastestReleaseMovies)
    Call<ApiResponse<List<Movie>>> getTopLastestReleaseMovies(@Query("top") int top);

}
