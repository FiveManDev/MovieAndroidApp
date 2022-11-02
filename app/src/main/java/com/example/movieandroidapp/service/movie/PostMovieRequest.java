package com.example.movieandroidapp.service.movie;

import com.example.movieandroidapp.contract.movie.PostUpdateMovieContract;
import com.example.movieandroidapp.model.ApiResponse;
import com.example.movieandroidapp.model.movie.PostMovie;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.Movie.IMovieApi;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostMovieRequest implements PostUpdateMovieContract.Model {
    @Override
    public void postOrUpdateMovie(OnFinishedListener onFinishedListener, PostMovie postMovie, String type) {

        //empty file
        RequestBody file = RequestBody.create(MultipartBody.FORM,"");

        RequestBody MovieID = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getMovieID());
        RequestBody MovieName = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getMovieName());
        RequestBody Description = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getDescription());
        MultipartBody.Part Thumbnail;
        if (postMovie.getThumbnail() == null) {

            Thumbnail = MultipartBody.Part.createFormData("Thumbnail",
                    "",file);
        } else {
            Thumbnail = MultipartBody.Part.createFormData("Thumbnail",
                    postMovie.getThumbnail().getName(),
                    RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getThumbnail()));
        }

        RequestBody Country = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getCountry());
        RequestBody Actor = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getActor());
        RequestBody Director = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getDirector());
        RequestBody Language = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getLanguage());
        RequestBody Subtitle = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getSubtitle());
        RequestBody ReleaseTime = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getReleaseTime());
        RequestBody PublicationTime = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getPublicationTime());
        MultipartBody.Part CoverImage;
        if (postMovie.getCoverImage() == null) {
            CoverImage = MultipartBody.Part.createFormData("CoverImage", "",file);
        } else {
            CoverImage = MultipartBody.Part.createFormData("CoverImage", postMovie.getCoverImage().getName(), RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getCoverImage()));
        }
        RequestBody Age = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getAge());
        MultipartBody.Part Movie;
        if (postMovie.getMovie() == null) {
            Movie = MultipartBody.Part.createFormData("Movie", "",file);
        } else {
            Movie = MultipartBody.Part.createFormData("Movie", postMovie.getMovie().getName(), RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getMovie()));
        }

        RequestBody RunningTime = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getRunningTime().toString());
        RequestBody Quality = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getQuality());
        RequestBody UserID = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getUserID());
        RequestBody ClassName = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getClassName());
        RequestBody MovieTypeName = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getMovieTypeName());
        RequestBody GenreNames = RequestBody.create(MediaType.parse("multipart/form-data"), postMovie.getGenreNames().get(0));

        IMovieApi apiService = ApiClient.getClient().create(IMovieApi.class);
        Call<ApiResponse<String[]>> call;
        if (type.equals("post")) {

            call = apiService.PostMovie(
                    MovieID,
                    MovieName,
                    Description,
                    Thumbnail,
                    Country,
                    Actor,
                    Director,
                    Language,
                    Subtitle,
                    ReleaseTime,
                    PublicationTime,
                    CoverImage,
                    Age,
                    Movie,
                    RunningTime,
                    Quality,
                    UserID,
                    ClassName,
                    MovieTypeName,
                    GenreNames
            );
        } else {
            call = apiService.UpdateMovie(
                    MovieID,
                    MovieName,
                    Description,
                    Thumbnail,
                    Country,
                    Actor,
                    Director,
                    Language,
                    Subtitle,
                    ReleaseTime,
                    PublicationTime,
                    CoverImage,
                    Age,
                    Movie,
                    RunningTime,
                    Quality,
                    UserID,
                    ClassName,
                    MovieTypeName,
                    GenreNames
            );
        }
        call.enqueue(new Callback<ApiResponse<String[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<String[]>> call, Response<ApiResponse<String[]>> response) {
                if (response.isSuccessful()) {
                    onFinishedListener.onFinished();
                } else {
                    onFinishedListener.onFailure("Cannot upload movie");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<String[]>> call, Throwable t) {
                onFinishedListener.onFailure("Error in Get movies");
            }
        });
    }
}
