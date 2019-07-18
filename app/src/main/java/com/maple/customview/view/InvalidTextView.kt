package com.maple.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView

/**
 * 自定义View
 *
 * @author maple
 * @time 2019/1/14
 */
class InvalidTextView : TextView {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initDraw()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initDraw()
    }

    private fun initDraw() {
        mPaint.color = Color.RED// 设置颜色
        mPaint.strokeWidth = 1.5f// 设置线宽度
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val mHeight = (height shr 1).toFloat() // mHeight = height / 2
        canvas.drawLine(0f, mHeight, width.toFloat(), mHeight, mPaint)
    }
}
