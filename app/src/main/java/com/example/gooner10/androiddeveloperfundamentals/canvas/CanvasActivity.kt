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

class CanvasActivity : AppCompatActivity() {
    private val OFFSET_CONSTANT = 120
    private val offset = OFFSET_CONSTANT
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
    }

    fun handleCanvas(view: View) {

    }
}
