package id.gits.core.widget;

import android.content.Context;
import android.util.AttributeSet;

public class MyImageViewSquare extends android.widget.ImageView {

    public MyImageViewSquare(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyImageViewSquare(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageViewSquare(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, width);
    }

}