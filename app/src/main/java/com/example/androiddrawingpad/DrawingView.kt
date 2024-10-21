package com.example.androiddrawingpad

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val path = Path()
    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 10f
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.moveTo(event.x, event.y)
            MotionEvent.ACTION_MOVE -> path.lineTo(event.x, event.y)
            MotionEvent.ACTION_UP -> {}
        }
        invalidate()
        return true
    }

    fun setColor(color: Int) {
        paint.color = color
    }

    fun setStrokeWidth(width: Float) {
        paint.strokeWidth = width
    }

    fun clear() {
        path.reset()
        invalidate()
    }
}
