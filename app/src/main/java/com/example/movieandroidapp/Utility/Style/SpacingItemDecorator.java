package com.example.movieandroidapp.Utility.Style;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacingItemDecorator extends RecyclerView.ItemDecoration {

    private final int horizontalSpaceWidth;
    private final int horizontalSpaceHeight;

    public SpacingItemDecorator(int horizontalSpaceWidth, int horizontalSpaceHeight) {
        this.horizontalSpaceWidth = horizontalSpaceWidth;
        this.horizontalSpaceHeight = horizontalSpaceHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (horizontalSpaceHeight != 0) {
            outRect.bottom = horizontalSpaceHeight;
        }
        if (horizontalSpaceWidth != 0) {
            outRect.right = horizontalSpaceWidth;
        }

    }
}
