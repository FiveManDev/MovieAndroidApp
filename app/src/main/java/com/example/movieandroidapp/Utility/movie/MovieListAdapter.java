package com.example.movieandroidapp.Utility.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.contract.movie.ListenerMovie;
import com.example.movieandroidapp.model.movie.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private List<Movie> movieList;
    private ListenerMovie listenerMovie;
    public MovieListAdapter(List<Movie> list,ListenerMovie listenerMovie){
        this.listenerMovie = listenerMovie;
        this.movieList = list;
    }

    @NonNull
    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieListAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        holder.title_movie.setText(movieList.get(position).getMovieName());
        holder.genre_movie.setText(String.join(", ",movieList.get(position).getGenres()));
        holder.rating_movie.setText(movieList.get(position).getRating().toString());
        Picasso.get().load(movieList.get(position).getCoverImage()).into(holder.img_movie);
        holder.movie_item_container.setOnClickListener(t->{
            listenerMovie.ClickedMovie(movieList.get(position),"detail");
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView img_movie;
        TextView title_movie, genre_movie, rating_movie;
        CardView movie_item_container;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            img_movie = itemView.findViewById(R.id.img_movie);
            title_movie = itemView.findViewById(R.id.title_movie);
            genre_movie = itemView.findViewById(R.id.genre_movie);
            rating_movie = itemView.findViewById(R.id.rating_movie);
            movie_item_container = itemView.findViewById(R.id.movie_item_container);
        }
    }
}
