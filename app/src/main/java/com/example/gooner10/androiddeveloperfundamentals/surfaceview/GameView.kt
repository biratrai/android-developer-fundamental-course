package com.example.gooner10.androiddeveloperfundamentals.surfaceview

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceView

/**
 * Custom SurfaceView class
 */
class GameView : SurfaceView, Runnable {
    private var mContext: Context? = null

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
    }

    override fun run() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun resume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}