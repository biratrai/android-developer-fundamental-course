package com.example.gooner10.androiddeveloperfundamentals.customview

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Custom ImageView class that shows rotation of a fan
 */
class FanControllerView : View {
    private val SELECTION_COUNT = 4 // Total number of selections.
    private val width: Float = 0.toFloat()                   // Custom view width.
    private val height: Float = 0.toFloat()                  // Custom view height.
    private var textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)               // For text in the view.
    private var dialPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)               // For dial circle in the view.
    private val radius: Float = 0.toFloat()                  // Radius of the circle.
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
    }
}