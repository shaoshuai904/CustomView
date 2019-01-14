package com.maple.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author maple
 * @time 2019/1/14
 */
@SuppressLint("AppCompatCustomView")
public class InvalidTextView extends TextView {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public InvalidTextView(Context context) {
        super(context);
        initDraw();
    }

    public InvalidTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDraw();
    }

    public InvalidTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }

    public void initDraw() {
        mPaint.setColor(Color.RED);// 设置颜色
        mPaint.setStrokeWidth(1.5f);// 设置线宽度
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawLine(0, height / 2, width, height / 2, mPaint);
    }
}
