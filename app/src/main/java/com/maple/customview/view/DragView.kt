package com.maple.customview.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * 随手指移动的view
 */
class DragView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var lastX = 0F
    private var lastY = 0F

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // 获取手指触摸点的横纵坐标,设置开始位置
                lastX = event.x
                lastY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                // 计算移动距离
                val offsetX = (event.x - lastX).toInt()
                val offsetY = (event.y - lastY).toInt()
                // 调用layout方法，重新放置
                // layout(left + offsetX, top + offsetY, right + offsetX, bottom + offsetY)
                // 另一种写法
                offsetLeftAndRight(offsetX)
                offsetTopAndBottom(offsetY)
            }
            MotionEvent.ACTION_UP -> {
            }
            MotionEvent.ACTION_CANCEL -> {
            }
        }
        return true
    }

}
