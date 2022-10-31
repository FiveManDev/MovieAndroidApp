package com.example.movieandroidapp.view.Review;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.contract.review.ListenerReview;
import com.example.movieandroidapp.model.Review;

import java.util.List;

public class ListReviewAdminAdapter extends RecyclerView.Adapter<ReviewAdminHolder> {
    private List<Review> list;
    private String type;
    private Context context;
    private ListenerReview listenerReview;
    public ListReviewAdminAdapter(List<Review> list,Context context, String type) {
        this.list = list;
        this.type = type;
        this.context = context;
    }
    public ListReviewAdminAdapter(List<Review> list,Context context, String type,ListenerReview ListenerReview) {
        this.list = list;
        this.type = type;
        this.context = context;
        this.listenerReview = ListenerReview;
    }

    public void setList(List<Review> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewAdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewAdminHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_admin_review, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdminHolder holder, int position) {
        Review review = list.get(position);
        holder.row_review_item_admin_id.setText(review.getReviewID());
        holder.row_review_item_admin_title.setText(review.getTitle());
        holder.row_review_item_admin_author.setText(review.getFirstName()+ " "+review.getLastName());
        holder.row_review_item_admin_rating.setText(review.getRating().toString());
        if(type.equals("dashboard")){
            holder.row_review_item_admin_content.setVisibility(View.GONE);
            holder.row_review_item_admin_date.setVisibility(View.GONE);
            holder.container_review_action.setVisibility(View.GONE);
        }
        else{
            holder.row_review_item_admin_content.setText(review.getReviewContent());
            holder.row_review_item_admin_date.setText(Extension.formatDate(review.getReviewTime()));
            holder.btn_delete_review_admin.setOnClickListener(t->{
                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context)
                        .setMessage("Do you want to Delete")
                        .setPositiveButton("Delete", (dialog, whichButton) -> {
                            listenerReview.listenerClicked(review,"delete");
                            dialog.dismiss();
                        })
                        .setNegativeButton("cancel", (dialog, which) -> dialog.dismiss())
                        .create();
                myQuittingDialogBox.show();
            });
            holder.btn_detail_review_admin.setOnClickListener(t->listenerReview.listenerClicked(review,"detail"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
