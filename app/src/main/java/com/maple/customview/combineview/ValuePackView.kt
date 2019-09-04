package com.maple.customview.combineview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.maple.customview.R

/**
 * 优惠包View
 *
 * @author maple
 * @time 2019-09-04
 */
class ValuePackView : RelativeLayout {
    private lateinit var llViewRoot: LinearLayout
    private lateinit var tvType: TextView
    private lateinit var tvNewValue: TextView
    private lateinit var tvOldValue: TextView
    private lateinit var tvTime: TextView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_value_pack, this, true)

        llViewRoot = findViewById(R.id.ll_view_root)
        tvType = findViewById(R.id.tv_type)
        tvNewValue = findViewById(R.id.tv_new_value)
        tvOldValue = findViewById(R.id.tv_old_value)
        tvTime = findViewById(R.id.tv_time)
    }

    // -------------------- public method --------------------------
    fun setSelectState(isChecked: Boolean) {
        // Todo 切换背景
        if (isChecked) {
            // llViewRoot.background = XXX
        } else {

        }
    }

    fun setType(type: CharSequence) {
        tvType.text = type
    }

    fun setNewValue(newValue: CharSequence) {
        tvNewValue.text = newValue
    }

    fun setOldValue(oldValue: Float) {
        tvOldValue.text = "¥ ${oldValue}"
    }

    fun setTime(day: Int) {
        tvTime.text = "有效时间${day}天"
    }

    fun setOnItemClickListener(listener: OnClickListener) {
        llViewRoot.setOnClickListener(listener)
    }

}
