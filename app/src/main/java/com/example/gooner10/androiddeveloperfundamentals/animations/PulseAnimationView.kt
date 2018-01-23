package com.example.gooner10.androiddeveloperfundamentals.animations

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Custom animation view
 */
class PulseAnimationView : View {
    private var radius: Float? = null
    private val paint: Paint = Paint()
    private val COLOR_ADJUSTER = 5
    private var mX: Float? = null
    private var mY: Float? = null
    private val ANIMATION_DURATION = 4000
    private val ANIMATION_DELAY = 1000

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    fun setRadius(radius: Float) {
        this.radius = radius
        paint.color = (Color.GREEN + radius / COLOR_ADJUSTER).toInt()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            this.mX = event.x
            this.mY = event.y
        }
        return super.onTouchEvent(event)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val growAnimator: ObjectAnimator = ObjectAnimator.ofFloat(this, "radius", 0F, width.toFloat())
        growAnimator.duration = ANIMATION_DURATION.toLong()
    }
}