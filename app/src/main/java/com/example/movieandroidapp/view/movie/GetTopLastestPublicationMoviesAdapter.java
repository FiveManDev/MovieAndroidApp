package com.example.movieandroidapp.view.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.model.movie.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GetTopLastestPublicationMoviesAdapter extends RecyclerView.Adapter<GetTopLastestPublicationMoviesAdapter.MyViewHolder> {

    private List<Movie> movieList;

    public GetTopLastestPublicationMoviesAdapter(List<Movie> list){
        this.movieList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GetTopLastestPublicationMoviesAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.title_movie.setText(movieList.get(position).getMovieName());
            holder.genre_movie.setText("Action");
            holder.rating_movie.setText(movieList.get(position).getRating().toString());
            Picasso.get().load(movieList.get(position).getCoverImage()).into(holder.img_movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView img_movie;
        TextView title_movie, genre_movie, rating_movie;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            img_movie = itemView.findViewById(R.id.img_movie);
            title_movie = itemView.findViewById(R.id.title_movie);
            genre_movie = itemView.findViewById(R.id.genre_movie);
            rating_movie = itemView.findViewById(R.id.rating_movie);
        }
    }
}
