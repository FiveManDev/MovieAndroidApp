package com.example.movieandroidapp.fragment.custom;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.model.Review;

public class DialogReview extends AppCompatDialogFragment {
    TextView dialog_title_review,dialog_info_review,dialog_content_review,dialog_rating_review;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Review review = Extension.GsonUtil().fromJson(getArguments().getString("Review"),Review.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_review_detail,null);
        builder.setView(view);

        dialog_title_review= view.findViewById(R.id.dialog_title_review);
        dialog_title_review.setText(review.getTitle());

        dialog_info_review= view.findViewById(R.id.dialog_info_review);
        dialog_info_review.setText(Extension.formatDate(review.getReviewTime()) + " by "+review.getFirstName()+review.getLastName());

        dialog_content_review= view.findViewById(R.id.dialog_content_review);
        dialog_content_review.setText(review.getReviewContent());

        dialog_rating_review= view.findViewById(R.id.dialog_rating_review);
        dialog_rating_review.setText(review.getRating().toString());
        return builder.create();

    }
}
