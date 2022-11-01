package com.example.movieandroidapp.network.Movie;

import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.Genre;
import com.example.movieandroidapp.model.Pagination;
import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.BodyRequest.ChangeStatusBody;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IMovieApi {

    //Thư mục khai báo các phương thức get,post,put,patch
    @GET(MovieApiUrl.GetTopLastestReleaseMovies)
    Call<ApiResponse<List<Movie>>> getTopLastestReleaseMovies(@Query("top") int top);

    @GET(MovieApiUrl.GetMoviesBasedOnGenre)
    Call<ApiResponse<List<Movie>>> GetMoviesBasedOnGenre(@Query("genreID") String genreID, @Query("top") int top);

    @GET(MovieApiUrl.GetMoviesBasedOnSearchText)
    Call<ApiResponse<List<Movie>>> GetMoviesBasedOnSearchText(@Query("searchText") String query);

    @GET(MovieApiUrl.GetMovieBaseOnFilter)
    Call<ApiResponse<List<Movie>>> GetMovieBaseOnFilter(@Query("genreID") String genreID,
                                                        @Query("quality") String quality,
                                                        @Query("ratingMin") String ratingMin,
                                                        @Query("ratingMax") String ratingMax,
                                                        @Query("releaseTimeMin") int releaseTimeMin,
                                                        @Query("releaseTimeMax") int releaseTimeMax
                                                        );

    @GET(MovieApiUrl.GetGenres)
    Call<ApiResponse<List<Genre>>> GetAllGenreOfMovie();

    @GET(MovieApiUrl.GetMovies)
    Call<ApiResponse<ResponseFilter<Movie[]>>> GetMovies(@Query("pageIndex") int pageIndex,
                                                         @Query("pageSize") int pageSize,
                                                         @Query("q") String q,
                                                         @Query("sortBy") String sortBy,
                                                         @Query("sortType") String sortType
                                                         );
    @GET(MovieApiUrl.GetTotalMovies)
    Call<ApiResponse<Integer>> GetTotalMovies();

    @DELETE(MovieApiUrl.DeleteMovie)
    Call<ApiResponse<String[]>> DeleteMovie(@Query("movieID") String movieID);

    @Multipart
    @POST(MovieApiUrl.PostMovie)
    Call<ApiResponse<String[]>> PostMovie(@Part("movieID") RequestBody movieID,
                                          @Part("MovieName") RequestBody MovieName,
                                          @Part("Description") RequestBody Description,
                                          @Part MultipartBody.Part Thumbnail,
                                          @Part("Country") RequestBody Country,
                                          @Part("Actor") RequestBody Actor,
                                          @Part("Director") RequestBody Director,
                                          @Part("Language") RequestBody Language,
                                          @Part("Subtitle") RequestBody Subtitle,
                                          @Part("ReleaseTime") RequestBody ReleaseTime,
                                          @Part("PublicationTime") RequestBody PublicationTime,
                                          @Part MultipartBody.Part CoverImage,
                                          @Part("Age") RequestBody Age,
                                          @Part MultipartBody.Part Movie,
                                          @Part("RunningTime") RequestBody RunningTime,
                                          @Part("Quality") RequestBody Quality,
                                          @Part("UserID") RequestBody UserID,
                                          @Part("ClassName") RequestBody ClassName,
                                          @Part("MovieTypeName") RequestBody MovieTypeName,
                                          @Part("GenreName") RequestBody GenreName
                                          );
    @PUT(MovieApiUrl.UpdateMovieStatus)
    Call<ApiResponse<String[]>> UpdateMovieStatus(@Body ChangeStatusBody statusBody);
}
