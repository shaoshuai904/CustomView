package com.maple.customview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 不拦截触摸事件的ScrollView
 *
 * @author maple
 * @time 2019-04-21
 */
public class NoInterceptScrollView extends ScrollView {
    public NoInterceptScrollView(Context context) {
        super(context);
    }

    public NoInterceptScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoInterceptScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
