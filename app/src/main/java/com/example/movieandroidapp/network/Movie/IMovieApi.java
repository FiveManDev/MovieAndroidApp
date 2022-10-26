package com.example.movieandroidapp.network.Movie;

import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.Genre;
import com.example.movieandroidapp.model.Pagination;
import com.example.movieandroidapp.model.movie.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Query;

public interface IMovieApi {

    //Thư mục khai báo các phương thức get,post,put,patch
    @GET(MovieApiUrl.GetTopLastestReleaseMovies)
    Call<ApiResponse<List<Movie>>> getTopLastestReleaseMovies(@Query("top") int top);

    @GET(MovieApiUrl.GetMoviesBasedOnGenre)
    Call<ApiResponse<List<Movie>>> GetMoviesBasedOnGenre(@Query("genreID") String genreID, @Query("top") int top);

    @GET(MovieApiUrl.GetMoviesBasedOnSearchText)
    Call<ApiResponse<List<Movie>>> GetMoviesBasedOnSearchText(@Query("searchText") String query);

    @GET(MovieApiUrl.GetGenres)
    Call<ApiResponse<List<Genre>>> GetAllGenreOfMovie();

    @GET(MovieApiUrl.GetMovies)
    Call<ApiResponse<Pagination<Movie[]>>> GetMovies(@Query("pageIndex") int pageIndex,
                                                         @Query("pageSize") int pageSize,
                                                         @Query("q") String q,
                                                         @Query("sortBy") String sortBy,
                                                         @Query("sortType") String sortType
                                                         );
    @GET(MovieApiUrl.GetTotalMovies)
    Call<ApiResponse<Integer>> GetTotalMovies();

    @HTTP(method = "DELETE",path = MovieApiUrl.DeleteMovie, hasBody = true)
    Call<ApiResponse<String[]>> DeleteMovie(@Body String movieID);
}
