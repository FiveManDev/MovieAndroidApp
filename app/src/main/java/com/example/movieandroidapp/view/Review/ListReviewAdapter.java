package com.example.movieandroidapp.view.Review;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.model.Review;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListReviewAdapter extends RecyclerView.Adapter<ListReviewAdapter.MyViewHolder> {
    private List<Review> list;

    public ListReviewAdapter(List<Review> list) {
        this.list = list;
    }

    public void addItems( Review review){
        list.add(review);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListReviewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_review_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Review review = list.get(position);
        holder.review_item_movie_title.setText(review.getTitle());
        String info = Extension.formatDate(review.getReviewTime()) + " by "+review.getFirstName()+review.getLastName();
        holder.review_item_movie_info.setText(info);
        holder.review_item_movie_content.setText(review.getReviewContent());
        holder.review_item_movie_rating.setText(review.getRating().toString());
        if(review.getAvatar() != null){
            Picasso.get().load(review.getAvatar()).into(holder.review_item_movie_avatar);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView review_item_movie_avatar;
        TextView review_item_movie_title, review_item_movie_info, review_item_movie_rating,review_item_movie_content;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            review_item_movie_title = itemView.findViewById(R.id.review_item_movie_title);
            review_item_movie_avatar = itemView.findViewById(R.id.review_item_movie_avatar);
            review_item_movie_info = itemView.findViewById(R.id.review_item_movie_info);
            review_item_movie_rating = itemView.findViewById(R.id.review_item_movie_rating);
            review_item_movie_content = itemView.findViewById(R.id.review_item_movie_content);
        }
    }
}
