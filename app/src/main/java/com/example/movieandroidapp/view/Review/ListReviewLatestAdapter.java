package com.example.movieandroidapp.view.Review;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.model.Review;

import java.util.List;

public class ListReviewLatestAdapter extends RecyclerView.Adapter<ListReviewLatestAdapter.MyViewHolder> {
    private List<Review> list;
    public ListReviewLatestAdapter(List<Review> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ListReviewLatestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListReviewLatestAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_admin_review, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListReviewLatestAdapter.MyViewHolder holder, int position) {
        Review review = list.get(position);
        holder.row_review_item_admin_id.setText(review.getReviewID());
        holder.row_review_item_admin_title.setText(review.getTitle());
        holder.row_review_item_admin_author.setText(review.getFirstName()+ " "+review.getLastName());
        holder.row_review_item_admin_rating.setText(review.getRating().toString());
        holder.row_review_item_admin_content.setVisibility(View.GONE);
        holder.row_review_item_admin_date.setVisibility(View.GONE);
        holder.container_review_action.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView row_review_item_admin_id,
                row_review_item_admin_title,
                row_review_item_admin_author,
                row_review_item_admin_content,
                row_review_item_admin_rating,
                row_review_item_admin_date;
        LinearLayout container_review_action;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            container_review_action = itemView.findViewById(R.id.container_review_action);

            row_review_item_admin_id = itemView.findViewById(R.id.row_review_item_admin_id);
            row_review_item_admin_title = itemView.findViewById(R.id.row_review_item_admin_title);
            row_review_item_admin_author = itemView.findViewById(R.id.row_review_item_admin_author);
            row_review_item_admin_content = itemView.findViewById(R.id.row_review_item_admin_content);
            row_review_item_admin_rating = itemView.findViewById(R.id.row_review_item_admin_rating);
            row_review_item_admin_date = itemView.findViewById(R.id.row_review_item_admin_date);
        }
    }
}
