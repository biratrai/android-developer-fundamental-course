package com.example.gooner10.androiddeveloperfundamentals.canvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.View
import com.example.gooner10.androiddeveloperfundamentals.R

/**
 * Custom Canvas View
 */
class DrawCanvasView : View {
    private val canvas: Canvas? = null
    private val bitmap: Bitmap? = null
    private val paint: Paint? = null
    private val path: Path? = null
    private val drawColor: Int? = null



    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        val backgroundColor = ResourcesCompat.getColor(resources, R.color.opaque_yellow, null)
    }

    constructor(context: Context?) : super(context) {

    }


//    public DrawCanvasView(Context context) {
//        this(context, null);
//    }
//
//    public DrawCanvasView(Context context, AttributeSet attributeSet)
//    {
//        super(context);
//
//    }
}