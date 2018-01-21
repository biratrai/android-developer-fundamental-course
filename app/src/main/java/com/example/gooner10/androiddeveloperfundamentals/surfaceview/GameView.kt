package com.example.gooner10.androiddeveloperfundamentals.surfaceview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.gooner10.androiddeveloperfundamentals.R

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
    private var viewWidth: Int? = null
    private var viewHeight: Int? = null
    private lateinit var winnerRect: RectF
    private var running: Boolean = false
    private var gameThread: Thread? = null
    private lateinit var flashLightCone: FlashlightCone

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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHeight = h
        flashLightCone = FlashlightCone(viewWidth!!, viewHeight!!)
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.android)
        setUpBitmap()
    }

    override fun run() {
    }

    fun pause() {
        running = false
        try {
            gameThread!!.join()
        } catch (ex: InterruptedException) {
            Log.e("Exception: ", ex.toString())
        }
    }

    fun resume() {
        running = true
        gameThread = Thread(this)
        gameThread!!.start()
    }

    fun setUpBitmap() {
        bitmapX = Math.floor(Math.random() * (viewWidth!! - bitmap.width)).toInt()
        bitmapY = Math.floor(Math.random() * (viewHeight!! - bitmap.height)).toInt()
        winnerRect = RectF(bitmapX!!.toFloat(), bitmapY!!.toFloat(), (bitmapX!! + bitmap.width).toFloat(), (bitmapY!! + bitmap.height).toFloat())
    }
}