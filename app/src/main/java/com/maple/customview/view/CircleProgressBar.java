package com.maple.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

import com.maple.customview.R;
import com.maple.customview.utils.DensityUtils;

/**
 * 圆形进度条，可内嵌不同状态的小icon
 *
 * @author : shaoshuai27
 * @date ：2020/4/6
 */
public class CircleProgressBar extends ProgressBar {
    private int mDefaultColor;//默认圆的颜色
    private int mReachedColor;//进度条的颜色
    private int mLineWidth;//进度条线的宽度
    private int mInterSize;//内部图形的大小
    private Drawable mInterDrawable; // 内部图形
    private Paint mPaint;

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDefaultColor = Color.parseColor("#E6E9EE");
        mReachedColor = Color.parseColor("#3171FE");
        mLineWidth = DensityUtils.dp2px(context, 3f);
        mInterSize = DensityUtils.dp2px(context, 22f);
        mInterDrawable = ContextCompat.getDrawable(context, R.drawable.svg_download_pause);
        setPaint();
    }

    private void setPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);//防抖动，绘制出来的图要更加柔和清晰
        mPaint.setStyle(Paint.Style.STROKE);//设置填充样式
        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置画笔笔刷类型
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        //为了保证最外层的圆弧全部显示，我们通常会设置自定义view的padding属性，这样就有了内边距，所以画笔应该平移到内边距的位置，这样画笔才会刚好在最外层的圆弧上
        //画笔平移到指定paddingLeft， getPaddingTop()位置
        canvas.translate(getPaddingStart(), getPaddingTop());

        int viewWidth = getMeasuredWidth() - getPaddingStart() - getPaddingEnd();
        int mRadius = viewWidth / 2;// 半径大小

//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.RED);
//        mPaint.setStrokeWidth(1);
//        canvas.drawRect(0, 0, viewWidth, viewWidth, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        //画默认圆(边框)的一些设置
        mPaint.setColor(mDefaultColor);
        mPaint.setStrokeWidth(mLineWidth);

        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);

        //画进度条的一些设置
        mPaint.setColor(mReachedColor);
        mPaint.setStrokeWidth(mLineWidth);
        //根据进度绘制圆弧
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        canvas.drawArc(new RectF(0, 0, viewWidth, viewWidth), -90, sweepAngle, false, mPaint);

        // 第二次偏移值，画内部状态图
        float padding2 = (viewWidth - mInterSize) / 2;
        canvas.translate(padding2, padding2);
//        mPaint.setColor(Color.RED);
//        mPaint.setStrokeWidth(1);
//        canvas.drawRect(0, 0, mInterSize, mInterSize, mPaint);
        if (mInterDrawable != null) {
            mInterDrawable.setBounds(0, 0, mInterSize, mInterSize);
            mInterDrawable.draw(canvas);
        } else {
            String text = "" + getProgress();
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(8);
            mPaint.setTextSize(50);
            mPaint.setTextAlign(Paint.Align.CENTER);
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
            float baseline = mInterSize / 2 + distance;
            canvas.drawText(text, mInterSize / 2, baseline, mPaint);
        }
        canvas.restore();
    }

    // 设置内部状态icon
    public void setInterIcon(@DrawableRes int resId) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), resId);
        setInterIcon(drawable);
    }

    // 设置内部状态icon
    public void setInterIcon(Drawable drawable) {
        this.mInterDrawable = drawable;
        invalidate();
    }
}