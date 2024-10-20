package com.example.androiddrawingpad

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_drawing.*

class DrawingView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var path = Path()
    private var paint = Paint()
    private lateinit var bitmap: Bitmap
    private lateinit var canvas: Canvas

    init {
        paint.isAntiAlias = true
        paint.strokeWidth = 10f
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                invalidate() 
            }
            MotionEvent.ACTION_UP -> {
                canvas.drawPath(path, paint)
                path.reset()
            }
        }
        return true
    }
    fun setColor(newColor: Int) {
        paint.color = newColor
    }

    fun setStrokeWidth(width: Float) {
        paint.strokeWidth = width
    }

    fun clear() {
        path.reset()
        bitmap.eraseColor(Color.TRANSPARENT)
        invalidate()
    }
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawing)

        // Handle color selection
        redButton.setOnClickListener { drawingView.setColor(Color.RED) }
        blueButton.setOnClickListener { drawingView.setColor(Color.BLUE) }
        greenButton.setOnClickListener { drawingView.setColor(Color.GREEN) }

        // Handle line width selection
        smallButton.setOnClickListener { drawingView.setStrokeWidth(10f) }
        mediumButton.setOnClickListener { drawingView.setStrokeWidth(20f) }
        largeButton.setOnClickListener { drawingView.setStrokeWidth(30f) }

        // Handle clearing the drawing
        clearButton.setOnClickListener { drawingView.clear() }
    }
}
