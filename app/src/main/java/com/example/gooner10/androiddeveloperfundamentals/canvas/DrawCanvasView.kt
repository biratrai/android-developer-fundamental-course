package com.example.gooner10.androiddeveloperfundamentals.canvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.gooner10.androiddeveloperfundamentals.R

/**
 * Custom Canvas View
 */
class DrawCanvasView : View {
    private val TAG = DrawCanvasView::class.java.simpleName
    private var canvas: Canvas? = null
    private var bitmap: Bitmap? = null
    private val paint: Paint = Paint()
    private val path: Path = Path()
    private var drawColor: Int? = null
    private var x: Float? = null
    private var y: Float? = null
    private val TOUCH_TOLERANCE = 4

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        val backgroundColor = ResourcesCompat.getColor(resources, R.color.opaque_yellow, null)
        drawColor = ResourcesCompat.getColor(resources, R.color.opaque_orange, null)
        paint.color = backgroundColor
        paint.isAntiAlias = true
        paint.isDither = true
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 12F
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
        canvas!!.drawColor(this.drawColor!!)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas.run {
            this!!.drawBitmap(bitmap, 0F, 0F, paint)
            this.drawPath(path, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        val any = when (event.action) {
            MotionEvent.ACTION_DOWN ->
                touchStart(x, y)
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                touchUp()
                invalidate()
            }
            else ->
                Log.d(TAG, "Other events")
        }
        return true
    }

    private fun touchUp() {
        path.lineTo(this.x!!, this.y!!)
        canvas!!.drawPath(path, paint)
        path.reset()
    }

    private fun touchMove(x: Float, y: Float) {
        if ((Math.abs(x - this.x!!) >= TOUCH_TOLERANCE) || (Math.abs(y - this.y!!) >= TOUCH_TOLERANCE)) {
            path.quadTo(this.x!!, this.y!!, (this.x!! + x) / 2, (this.y!! + y) / 2)
        }
    }

    private fun touchStart(x: Float, y: Float) {
        path.moveTo(x, y)
        this.x = x
        this.y = y
    }
}