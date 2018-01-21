package com.example.gooner10.androiddeveloperfundamentals.surfaceview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

/**
 * Custom SurfaceView class
 */
class GameView : SurfaceView, Runnable {
    private lateinit var mContext: Context
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var paint: Paint
    private lateinit var path: Path
    private var bitmapY: Int? = null
    private var bitmapX: Int? = null
    private lateinit var bitmap: Bitmap
    private val viewWidth: Int? = null
    private val viewHeight: Int? = null
    private lateinit var winnerRect: RectF

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        mContext = context
        surfaceHolder = holder
        paint = Paint()
        paint.color = Color.DKGRAY
        path = Path()
    }

    override fun run() {
    }

    fun pause() {
    }

    fun resume() {
    }

    fun setUpBitmap() {
        bitmapX = Math.floor(Math.random() * (viewWidth!! - bitmap.width)).toInt()
        bitmapY = Math.floor(Math.random() * (viewHeight!! - bitmap.height)).toInt()
        winnerRect = RectF(bitmapX!!.toFloat(), bitmapY!!.toFloat(), (bitmapX!! + bitmap.width).toFloat(), (bitmapY!! + bitmap.height).toFloat())
    }
}