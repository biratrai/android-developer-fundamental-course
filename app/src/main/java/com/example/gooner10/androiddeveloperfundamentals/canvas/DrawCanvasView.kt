package com.example.gooner10.androiddeveloperfundamentals.canvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.View
import com.example.gooner10.androiddeveloperfundamentals.R

/**
 * Custom Canvas View
 */
class DrawCanvasView : View {
    private var canvas: Canvas? = null
    private var bitmap: Bitmap? = null
    private val paint: Paint = Paint()
    private val path: Path = Path()
    private var drawColor: Int? = null

    constructor(context: Context?) : super(context)

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
}