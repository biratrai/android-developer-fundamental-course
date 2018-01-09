package com.example.gooner10.androiddeveloperfundamentals.canvas

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.example.gooner10.androiddeveloperfundamentals.R
import kotlinx.android.synthetic.main.activity_canvas.*

class CanvasActivity : AppCompatActivity() {
    private val OFFSET_CONSTANT = 120
    private var offset = OFFSET_CONSTANT
    private var canvas: Canvas? = null
    private val paint: Paint = Paint()
    private val paintText: Paint = Paint(Paint.UNDERLINE_TEXT_FLAG)
    private var bitmap: Bitmap? = null
    private var imageView: ImageView? = null
    private val rect: Rect = Rect()
    private val bounds: Rect = Rect()
    private val MULTIPLIER = 100
    private var colorBackground: Int? = null
    private var colorRectangle: Int? = null
    private var colorAccent: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas)
        colorBackground = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
        colorRectangle = ResourcesCompat.getColor(resources, R.color.colorRectangle, null)
        colorAccent = ResourcesCompat.getColor(resources, R.color.colorAccent, null)
        paint.color = colorBackground as Int
        paintText.color = ResourcesCompat.getColor(resources, R.color.colorPrimaryDark, null)
        imageView = canvas_imageview
    }

    fun handleCanvas(view: View) {
        val width = view.width
        val height = view.height
        val halfWidth = width / 2
        val halfHeight = height / 2
        if (offset == OFFSET_CONSTANT) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            imageView!!.setImageBitmap(bitmap)
            canvas = Canvas(bitmap)
            canvas!!.drawColor(this.colorBackground!!)
            canvas!!.drawText(getString(R.string.keep_tapping), 100F, 100F, paintText)
            offset += OFFSET_CONSTANT
        } else {
            if (offset < halfWidth && offset < halfHeight) {
                paint.color = colorRectangle!! - MULTIPLIER * offset
                rect.set(offset, offset, width - offset, height - offset)
                canvas!!.drawRect(rect, paint)
                offset += OFFSET_CONSTANT
            } else {
                paint.color = this.colorAccent!!
                canvas!!.drawCircle(halfWidth.toFloat(), halfHeight.toFloat(), (halfWidth / 3).toFloat(), paint)
                val text = getString(R.string.done)
                paintText.getTextBounds(text, 0, text.length, bounds)
                val x = halfWidth - bounds.centerX()
                val y = halfHeight - bounds.centerY()
                canvas!!.drawText(text, x.toFloat(), y.toFloat(), paintText)
            }

        }
        view.invalidate()
    }
}
