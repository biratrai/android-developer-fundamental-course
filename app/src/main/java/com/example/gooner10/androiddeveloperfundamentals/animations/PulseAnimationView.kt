package com.example.gooner10.androiddeveloperfundamentals.animations

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Custom animation view
 */
class PulseAnimationView : View {
    private var radius: Float? = null
    private val paint: Paint = Paint()
    private val COLOR_ADJUSTER = 5

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    fun setRadius(radius: Float) {
        this.radius = radius
        paint.color = (Color.GREEN + radius / COLOR_ADJUSTER).toInt()
        invalidate()
    }
}