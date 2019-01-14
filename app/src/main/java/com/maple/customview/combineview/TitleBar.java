package com.maple.customview.combineview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maple.customview.R;

/**
 * 自定义组合控件
 *
 * @author maple
 * @time 2019/1/14
 */
public class TitleBar extends RelativeLayout {
    private RelativeLayout rl_title_bar;
    private TextView tv_left;
    private TextView tv_title;
    private TextView tv_right;

    public TitleBar(Context context) {
        super(context);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义属性值
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_titile_bar, this, true);

        rl_title_bar = findViewById(R.id.rl_title_bar);
        tv_left = findViewById(R.id.tv_left);
        tv_title = findViewById(R.id.tv_title);
        tv_right = findViewById(R.id.tv_right);
    }

    // -------------------- public method --------------------------
    public void setTitle(CharSequence title) {
        tv_title.setText(title);
    }

    public void setOnLeftButtonClickListener(OnClickListener listener) {
        tv_left.setOnClickListener(listener);
    }

    public void setOnRightButtonClickListener(OnClickListener listener) {
        tv_right.setOnClickListener(listener);
    }

}
