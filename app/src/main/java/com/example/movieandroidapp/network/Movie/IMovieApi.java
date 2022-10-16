package com.example.movieandroidapp.network.Movie;

import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.Genre;
import com.example.movieandroidapp.model.movie.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMovieApi {

    //Thư mục khai báo các phương thức get,post,put,patch
    @GET(MovieApiUrl.GetTopLastestReleaseMovies)
    Call<ApiResponse<List<Movie>>> getTopLastestReleaseMovies(@Query("top") int top);

    @GET(MovieApiUrl.GetTopLastestPublicationMovies)
    Call<ApiResponse<List<Movie>>> GetTopLastestPublicationMovies(@Query("top") int top);

    @GET(MovieApiUrl.GetMoviesBasedOnSearchText)
    Call<ApiResponse<List<Movie>>> GetMoviesBasedOnSearchText(@Query("searchText") String query);

    @GET(MovieApiUrl.GetGenres)
    Call<ApiResponse<List<Genre>>> GetAllGenreOfMovie();
}
