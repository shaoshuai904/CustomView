package com.maple.customview.combineview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.maple.customview.R

/**
 * 自定义组合控件
 *
 * @author maple
 * @time 2019/1/14
 */
class TitleBar : RelativeLayout {
    private lateinit var rlTitleBar: RelativeLayout
    private lateinit var tvLeft: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvRight: TextView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_titile_bar, this, true)

        rlTitleBar = findViewById(R.id.rl_title_bar)
        tvLeft = findViewById(R.id.tv_left)
        tvTitle = findViewById(R.id.tv_title)
        tvRight = findViewById(R.id.tv_right)
    }

    // -------------------- public method --------------------------

    fun setTitle(title: CharSequence) {
        tvTitle.text = title
    }

    fun setLeftButtonText(title: CharSequence) {
        tvLeft.text = title
    }

    fun setOnLeftButtonClickListener(listener: OnClickListener) {
        tvLeft.setOnClickListener(listener)
    }

    fun setRightButtonText(title: CharSequence) {
        tvRight.text = title
    }

    fun setOnRightButtonClickListener(listener: OnClickListener) {
        tvRight.setOnClickListener(listener)
    }

}
