package id.gits.core.widget;

import android.content.Context;
import android.util.AttributeSet;

public class MyImageView21 extends android.widget.ImageView {

	public MyImageView21(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyImageView21(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyImageView21(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		setMeasuredDimension(width, width / 2);
	}

}