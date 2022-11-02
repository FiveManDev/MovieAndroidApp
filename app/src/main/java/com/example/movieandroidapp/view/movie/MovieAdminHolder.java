package com.example.movieandroidapp.view.movie;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;

public class MovieAdminHolder extends RecyclerView.ViewHolder {
    TextView row_movie_item_admin_id, row_movie_item_admin_name, row_movie_item_admin_genre,
            row_movie_item_admin_rating, row_movie_item_admin_visible, row_movie_item_admin_releaseTime,
            btn_delete_movie_admin, btn_block_movie_admin,btn_update_movie_admin;
    LinearLayout movie_item_admin_action;

    public MovieAdminHolder(@NonNull View itemView) {

        super(itemView);
        movie_item_admin_action = itemView.findViewById(R.id.movie_item_admin_action);

        row_movie_item_admin_id = itemView.findViewById(R.id.row_movie_item_admin_id);
        row_movie_item_admin_name = itemView.findViewById(R.id.row_movie_item_admin_name);
        row_movie_item_admin_genre = itemView.findViewById(R.id.row_movie_item_admin_genre);
        row_movie_item_admin_rating = itemView.findViewById(R.id.row_movie_item_admin_rating);
        row_movie_item_admin_visible = itemView.findViewById(R.id.row_movie_item_admin_visible);
        row_movie_item_admin_releaseTime = itemView.findViewById(R.id.row_movie_item_admin_releaseTime);

        btn_delete_movie_admin = itemView.findViewById(R.id.btn_delete_movie_admin);
        btn_block_movie_admin = itemView.findViewById(R.id.btn_block_movie_admin);
        btn_update_movie_admin = itemView.findViewById(R.id.btn_update_movie_admin);
    }
}
