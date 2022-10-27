package com.example.movieandroidapp.view.user;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;

public class UserAdminHolder extends RecyclerView.ViewHolder{
    TextView row_user_item_admin_id,row_user_item_admin_name,
            row_user_item_admin_email,row_user_item_admin_userName,row_user_item_admin_className,
            row_user_item_admin_review,row_user_item_admin_visible,row_user_item_admin_createAt,
            btn_block_review_admin,btn_delete_delete_admin;
    LinearLayout row_user_item_admin_info,container_user_action;
    public UserAdminHolder(@NonNull View itemView) {
        super(itemView);
        row_user_item_admin_id = itemView.findViewById(R.id.row_user_item_admin_id);
        row_user_item_admin_info = itemView.findViewById(R.id.row_user_item_admin_info);
        row_user_item_admin_name = itemView.findViewById(R.id.row_user_item_admin_name);
        row_user_item_admin_email = itemView.findViewById(R.id.row_user_item_admin_email);
        row_user_item_admin_userName = itemView.findViewById(R.id.row_user_item_admin_userName);
        row_user_item_admin_className = itemView.findViewById(R.id.row_user_item_admin_className);
        row_user_item_admin_review = itemView.findViewById(R.id.row_user_item_admin_review);
        row_user_item_admin_visible = itemView.findViewById(R.id.row_user_item_admin_visible);
        row_user_item_admin_createAt = itemView.findViewById(R.id.row_user_item_admin_createAt);
        container_user_action = itemView.findViewById(R.id.container_user_action);
        btn_block_review_admin = itemView.findViewById(R.id.btn_block_review_admin);
        btn_delete_delete_admin = itemView.findViewById(R.id.btn_delete_delete_admin);
    }
}
