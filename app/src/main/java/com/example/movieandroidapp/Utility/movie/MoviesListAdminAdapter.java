package com.example.movieandroidapp.Utility.movie;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.contract.movie.ListenerMovie;
import com.example.movieandroidapp.model.movie.Movie;

import java.util.List;

public class MoviesListAdminAdapter extends RecyclerView.Adapter<MovieAdminHolder> {

    private List<Movie> movieList;
    private Context context;
    private ListenerMovie listenerMovie;

    public MoviesListAdminAdapter(List<Movie> list, Context context, ListenerMovie listenerMovie) {
        this.movieList = list;
        this.context = context;
        this.listenerMovie = listenerMovie;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        this.notifyDataSetChanged();
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
            if (movie.getIsVisible()) {
                holder.row_movie_item_admin_visible.setText("Visible");
                holder.row_movie_item_admin_visible.setTextColor(Color.parseColor("#5bceae"));
            } else {
                holder.row_movie_item_admin_visible.setText("Hidden");
                holder.row_movie_item_admin_visible.setTextColor(Color.parseColor("#ff5860"));
            }
            holder.row_movie_item_admin_releaseTime.setText(Extension.formatDate(movie.getReleaseTime()));

            //handle click delete
            holder.btn_delete_movie_admin.setOnClickListener(t -> {
                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context)
                        // set message, title, and icon
                        .setTitle("Delete")
                        .setMessage("Do you want to Delete")
                        .setPositiveButton("Delete", (dialog, whichButton) -> {
                            listenerMovie.ClickedMovie(movie, "delete");
                            dialog.dismiss();
                        })
                        .setNegativeButton("cancel", (dialog, which) -> dialog.dismiss())
                        .create();
                myQuittingDialogBox.show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

}
