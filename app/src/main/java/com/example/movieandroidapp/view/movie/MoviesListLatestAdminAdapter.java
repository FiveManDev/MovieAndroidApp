package com.example.movieandroidapp.view.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.model.movie.Movie;

import java.util.List;

public class MoviesListLatestAdminAdapter extends RecyclerView.Adapter<MovieAdminHolder>{

    private List<Movie> movieList;

    public MoviesListLatestAdminAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieAdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieAdminHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie_item_admin, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdminHolder holder, int position) {
        if (movieList.size() > 0) {
            Movie movie = movieList.get(position);
            holder.row_movie_item_admin_id.setText(movie.getMovieID());
            holder.row_movie_item_admin_genre.setText(String.join(", ", movie.getGenres()));
            holder.row_movie_item_admin_rating.setText(movie.getRating().toString());
            holder.row_movie_item_admin_name.setText(movie.getMovieName());
            holder.row_movie_item_admin_visible.setVisibility(View.GONE);
            holder.row_movie_item_admin_releaseTime.setVisibility(View.GONE);
            holder.movie_item_admin_action.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
