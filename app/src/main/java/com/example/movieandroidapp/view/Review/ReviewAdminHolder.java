package com.example.movieandroidapp.view.Review;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;

public class ReviewAdminHolder extends  RecyclerView.ViewHolder{
    TextView row_review_item_admin_id,
            row_review_item_admin_title,
            row_review_item_admin_author,
            row_review_item_admin_content,
            row_review_item_admin_rating,
            row_review_item_admin_date,
            btn_delete_review_admin,
            btn_detail_review_admin
        ;
    LinearLayout container_review_action;
    public ReviewAdminHolder(@NonNull View itemView) {
        super(itemView);
        container_review_action = itemView.findViewById(R.id.container_review_action);
        btn_delete_review_admin = itemView.findViewById(R.id.btn_delete_review_admin);
        btn_detail_review_admin = itemView.findViewById(R.id.btn_detail_review_admin);
        row_review_item_admin_id = itemView.findViewById(R.id.row_review_item_admin_id);
        row_review_item_admin_title = itemView.findViewById(R.id.row_review_item_admin_title);
        row_review_item_admin_author = itemView.findViewById(R.id.row_review_item_admin_author);
        row_review_item_admin_content = itemView.findViewById(R.id.row_review_item_admin_content);
        row_review_item_admin_rating = itemView.findViewById(R.id.row_review_item_admin_rating);
        row_review_item_admin_date = itemView.findViewById(R.id.row_review_item_admin_date);
    }
}

