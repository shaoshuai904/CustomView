package com.maple.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.maple.customview.R
import kotlin.math.max

/**
 * 自定义View继承View时，注意事项⚠️
 * 1、对 padding 进行处理
 * 2、对 wrap_content 进行处理,设置默认大小
 *
 * @author maple
 * @time 2019/1/14
 */
class RectView : View {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mColor = Color.RED
    private var mRect = Rect()

    private val defWidth = 60
    private val defHeight = 60

    constructor(context: Context) : super(context) {
        initDraw()
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView)
        mColor = typedArray.getColor(R.styleable.RectView_rect_color, mColor)// 获取自定义属性值
        typedArray.recycle()// 及时回收资源

        initDraw()
    }

    private fun initDraw() {
        mPaint.color = mColor// 设置颜色
        mPaint.strokeWidth = 1.5f// 设置线宽度
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 计算width
        val widthSize = if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
            // 如果width是wrap_content,则需要动态设置width的大小
            max(minimumWidth, defWidth)
        } else {
            // 如果width是match_parent，则返回父容器宽度。
            // 如果width是 xx dp,则返回具体的值。
            MeasureSpec.getSize(widthMeasureSpec)
        }
        // 计算height
        val heightSize = if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            // 如果height是任意大小，则设置为：defHeight
            max(minimumHeight, defHeight)
        } else {
            MeasureSpec.getSize(heightMeasureSpec)
        }

        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 画矩形
        mRect.set(0 + paddingLeft,
                0 + paddingTop,
                width - paddingRight,
                height - paddingBottom)
        canvas.drawRect(mRect, mPaint)
        // 画文本
        mPaint.apply {
            color = Color.RED
            style = Paint.Style.FILL
            textAlign = Paint.Align.CENTER
            textSize = 50F
        }
        val font = mPaint.fontMetrics
        val baseLineY = mRect.centerY() - font.top / 2 - font.bottom / 2
        canvas.drawText("Hi",
                mRect.centerX().toFloat(),
                baseLineY,
                mPaint)
    }
}
