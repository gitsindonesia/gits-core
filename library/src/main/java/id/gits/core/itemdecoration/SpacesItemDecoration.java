package id.gits.core.itemdecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int mColumnCount;

    public SpacesItemDecoration(int space, int columnCount) {
        this.space = space;
        mColumnCount = columnCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildAdapterPosition(view) < mColumnCount) //first row
//            outRect.top = space;

        if (parent.getChildAdapterPosition(view) % mColumnCount == 0) {
            outRect.right = space / 2;
//            outRect.left = space;
        } else if (parent.getChildAdapterPosition(view) % mColumnCount == mColumnCount - 1) {
            outRect.left = space / 2;
//            outRect.right = space;
        } else {
            outRect.left = space / 2;
            outRect.right = space / 2;
        }

    }
}