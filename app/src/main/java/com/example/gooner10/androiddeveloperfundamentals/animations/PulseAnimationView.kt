package com.example.gooner10.androiddeveloperfundamentals.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * Custom animation view
 */
class PulseAnimationView : View {
    private var radius: Float = 0.0F
    private val paint: Paint = Paint()
    private val COLOR_ADJUSTER = 5
    private var mX: Float = 0.0F
    private var mY: Float = 0.0F
    private val ANIMATION_DURATION = 4000
    private val ANIMATION_DELAY = 1000
    private val pulseAnimatorSet: AnimatorSet = AnimatorSet()

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

        if (pulseAnimatorSet.isRunning) {
            pulseAnimatorSet.cancel()
        }
        pulseAnimatorSet.start()
        return super.onTouchEvent(event)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val growAnimator: ObjectAnimator = ObjectAnimator.ofFloat(this, "radius", 0F, width.toFloat())
        growAnimator.duration = ANIMATION_DURATION.toLong()
        growAnimator.interpolator = LinearInterpolator()

        val shrinkAnimator: ObjectAnimator = ObjectAnimator.ofFloat(this, "radius", width.toFloat(), 0F)
        shrinkAnimator.duration = ANIMATION_DURATION.toLong()
        shrinkAnimator.interpolator = LinearOutSlowInInterpolator()
        shrinkAnimator.startDelay = ANIMATION_DELAY.toLong()

        val repeatAnimator: ObjectAnimator = ObjectAnimator.ofFloat(this, "radius", 0F, width.toFloat())
        repeatAnimator.startDelay = ANIMATION_DELAY.toLong()
        repeatAnimator.duration = ANIMATION_DURATION.toLong()
        repeatAnimator.repeatCount = 1
        repeatAnimator.repeatMode = ValueAnimator.REVERSE

        pulseAnimatorSet.play(growAnimator).before(shrinkAnimator)
        pulseAnimatorSet.play(repeatAnimator).after(shrinkAnimator)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.i("PulseAnimation: ", "radius: " + radius)
        canvas.drawCircle(mX!!, mY!!, radius!!, paint)
    }


}