package com.example.movieandroidapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.movieandroidapp.Activity.HomeActivity;
import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.model.movie.Movie;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MovieDetailFragment extends Fragment {
    Button watchBtn;
    TextView movie_title_detail,
            movie_rating_detail,
            movie_quality_detail,
            movie_age_detail,
            movie_genre_detail,
            movie_time_detail,
            movie_release_detail,
            movie_country_detail,
            movie_desc_detail;

    ImageView movie_image_detail;
    Movie movie;
    View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_movie_detail,container,false);
        Gson gson = new Gson();
        movie =gson.fromJson(this.getArguments().getString("movie"),Movie.class) ;
        init();
        return mView;
    }
    private void init(){
        render();
        clickButtonWatch();
    }
    private void render(){
        movie_image_detail = mView.findViewById(R.id.movie_image_detail);

        movie_title_detail = mView.findViewById(R.id.movie_title_detail);
        movie_rating_detail = mView.findViewById(R.id.movie_rating_detail);
        movie_quality_detail = mView.findViewById(R.id.movie_quality_detail);
        movie_age_detail = mView.findViewById(R.id.movie_age_detail);
        movie_genre_detail = mView.findViewById(R.id.movie_genre_detail);
        movie_time_detail = mView.findViewById(R.id.movie_time_detail);
        movie_release_detail = mView.findViewById(R.id.movie_release_detail);
        movie_country_detail = mView.findViewById(R.id.movie_country_detail);
        movie_desc_detail = mView.findViewById(R.id.movie_desc_detail);

        Picasso.get().load(movie.getCoverImage()).into(movie_image_detail);

        movie_title_detail.setText(movie.getMovieName());
        movie_rating_detail.setText(movie.getRating().toString());
        movie_quality_detail.setText(movie.getQuality());
        movie_age_detail.setText(movie.getAge());
        movie_genre_detail.setText(String.join(", ",movie.getGenres()));
        movie_time_detail.setText(movie.getRunningTime().toString());
        movie_release_detail.setText(Extension.formatDate(movie.getReleaseTime()));
        movie_country_detail.setText(movie.getCountry());
        movie_desc_detail.setText(movie.getDescription());
    }

    private void clickButtonWatch(){
        watchBtn = mView.findViewById(R.id.btn_watch_movie);
        watchBtn.setOnClickListener(t -> {
            WatchMovieFragment movieDetailFragment = new WatchMovieFragment();
            ((HomeActivity) getActivity()).replaceFragment(movieDetailFragment);
        });
    }
}
