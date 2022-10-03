package com.example.movieandroidapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.model.Movie;
import com.example.movieandroidapp.network.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private List<Movie> movieList;

    public MovieListAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_movie_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvMovietitle.setText(movieList.get(position).getTitle());
        holder.tvReleaseDate.setText(movieList.get(position).getReleaseDate());
        holder.tvOverview.setText(movieList.get(position).getOverview());
        if(movieList.get(position).getPosterPath()!=null){
            Picasso.get().load(movieList.get(position).getPosterPath()).into(holder.ivMovie);
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        ImageView ivMovie;
        TextView tvMovietitle, tvReleaseDate, tvOverview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivMovie = itemView.findViewById(R.id.ivMovie);
            tvMovietitle = itemView.findViewById(R.id.tvTitleMovie);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseMovie);
            tvOverview = itemView.findViewById(R.id.tvOverviewMovie);

        }
    }
}
