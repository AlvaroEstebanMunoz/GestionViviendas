package com.aem.gestionalquileres.utilidades;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class DecoracionEspacioFinal extends RecyclerView.ItemDecoration {
    private final int space;
    private final boolean isHorizontal;

    public DecoracionEspacioFinal(int space, boolean isHorizontal) {
        this.space = space;
        this.isHorizontal = isHorizontal;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (isHorizontal) {
            // Apply spacing for horizontal orientation
            outRect.right = space;
            if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
                // Add extra space on the right side of the last item
                outRect.right = space * 2; // Adjust as needed
            }
            // Add space on the left side for all items
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = space;
            }
        } else {
            // Apply spacing for vertical orientation
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
                // Add extra space on the bottom of the last item
                outRect.bottom = space * 2; // Adjust as needed
            }
            // Add space on the top for all items
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }
}
