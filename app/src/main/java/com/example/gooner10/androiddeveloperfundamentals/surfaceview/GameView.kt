package com.example.gooner10.androiddeveloperfundamentals.surfaceview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
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
        while (running) {
            if (surfaceHolder.surface.isValid) {
                val x = flashLightCone.x
                val y = flashLightCone.y
                val radius = flashLightCone.radius
                val canvas = surfaceHolder.lockCanvas()
                canvas.save()
                canvas.drawColor(Color.WHITE)
                canvas.drawBitmap(bitmap, bitmapX!!.toFloat(), bitmapY!!.toFloat(), paint)
                path.addCircle(x!!.toFloat(), y!!.toFloat(), radius!!.toFloat(), Path.Direction.CCW)
                canvas.clipPath(path, Region.Op.DIFFERENCE)
                canvas.drawColor(Color.BLUE)
                if (x > winnerRect.left && x < winnerRect.right
                        && y > winnerRect.top && y < winnerRect.bottom) {
                    canvas.drawColor(Color.WHITE)
                    canvas.drawBitmap(bitmap, bitmapX!!.toFloat(), bitmapY!!.toFloat(), paint)
                    canvas.drawText(
                            "WIN!", (viewWidth!! / 3).toFloat(), (viewHeight!! / 2).toFloat(), paint)
                }
                path.rewind()
                canvas.restore()
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        // Invalidate() is inside the case statements because there are
        // many other motion events, and we don't want to invalidate
        // the view for those.
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                setUpBitmap()
                // Set coordinates of flashlight cone.
                updateFrame(x.toInt(), y.toInt())
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                // Updated coordinates for flashlight cone.
                updateFrame(x.toInt(), y.toInt())
                invalidate()
            }
        }// Do nothing.
        return true
    }

    private fun updateFrame(newX: Int, newY: Int) {
        flashLightCone.update(newX, newY)
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