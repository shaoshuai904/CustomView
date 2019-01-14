package com.maple.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.maple.customview.R;

/**
 * 自定义View继承View时，注意事项⚠️
 * 1、对 padding 进行处理
 * 2、对 wrap_content 进行处理,设置默认大小
 *
 * @author maple
 * @time 2019/1/14
 */
public class RectView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor = Color.RED;

    public RectView(Context context) {
        super(context);
        initDraw();
    }

    public RectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView);
        mColor = typedArray.getColor(R.styleable.RectView_rect_color, mColor);// 获取自定义属性值
        typedArray.recycle();// 及时回收资源
        initDraw();
    }

    public RectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }

    public void initDraw() {
        mPaint.setColor(mColor);// 设置颜色
        mPaint.setStrokeWidth(1.5f);// 设置线宽度
    }

    final int defWidth = 60;
    final int defHeight = 60;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize;
        int heightSize;

        // 计算width
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
            // 如果width是wrap_content,则需要动态设置width的大小
            int minWidth = getMinimumWidth();
            widthSize = Math.max(minWidth, defWidth);
        } else {
            // 如果width是match_parent，则返回父容器宽度。
            // 如果width是 xx dp,则返回具体的值。
            widthSize = MeasureSpec.getSize(widthMeasureSpec);
        }
        // 计算height
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            // 如果height是任意大小，则设置为：defHeight
            int minHeight = getMinimumHeight();
            heightSize = Math.max(minHeight, defHeight);
        } else {
            heightSize = MeasureSpec.getSize(heightMeasureSpec);
        }

        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 对padding值进行处理
        int left = getPaddingLeft();
        int right = getPaddingRight();
        int top = getPaddingTop();
        int bottom = getPaddingBottom();
        // view实际大小
        int width = getWidth() - left - right;
        int height = getHeight() - top - bottom;
        //
        canvas.drawRect(0 + left, 0 + top, width + right, height + bottom, mPaint);
    }
}
