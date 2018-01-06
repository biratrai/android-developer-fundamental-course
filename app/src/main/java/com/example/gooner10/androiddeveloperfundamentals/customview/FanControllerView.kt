package com.example.gooner10.androiddeveloperfundamentals.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Custom ImageView class that shows rotation of a fan
 */
class FanControllerView : View {
    private val SELECTION_COUNT = 4 // Total number of selections.
    private var width: Float = 0.toFloat()                   // Custom view width.
    private var height: Float = 0.toFloat()                  // Custom view height.
    private var textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)               // For text in the view.
    private var dialPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)               // For dial circle in the view.
    private var radius: Float = 0.toFloat()                  // Radius of the circle.
    private var activeSelection: Int = 0           // The active selection.
    // String buffer for dial labels and float for ComputeXY result.
    private val tempLabel = StringBuffer(8)
    private val tempResult = FloatArray(2)

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        textPaint.color = Color.BLACK
        textPaint.style = Paint.Style.FILL_AND_STROKE
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = 40f
        dialPaint.color = Color.GRAY
        // Initialize current selection.
        activeSelection = 0
        // TODO: Set up onClick listener for this view.
        setOnClickListener { v -> v.isClickable }
    }

    private fun computeXYForPosition(pos: Int, radius: Float): FloatArray {
        val result = tempResult
        val startAngle = Math.PI * (9 / 8.0)   // Angles are in radians.
        val angle = startAngle + pos * (Math.PI / 4)
        result[0] = (radius * Math.cos(angle)).toFloat() + width / 2
        result[1] = (radius * Math.sin(angle)).toFloat() + height / 2
        return result
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        width = w.toFloat()
        height = h.toFloat()
        radius = (Math.min(width, height) / 2 * 0.8).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw the dial.
        canvas.drawCircle(width / 2, height / 2, radius, dialPaint)
        // Draw the text labels.
        val labelRadius = radius + 20
        val label = tempLabel
        for (i in 0 until SELECTION_COUNT) {
            val xyData = computeXYForPosition(i, labelRadius)
            val x = xyData[0]
            val y = xyData[1]
            label.setLength(0)
            label.append(i)
            canvas.drawText(label, 0, label.length, x, y, textPaint)
        }
        // Draw the indicator mark.
        val markerRadius = radius - 35
        val xyData = computeXYForPosition(activeSelection,
                markerRadius)
        val x = xyData[0]
        val y = xyData[1]
        canvas.drawCircle(x, y, 20f, textPaint)
    }
}