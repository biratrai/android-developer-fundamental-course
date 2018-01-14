package com.example.gooner10.androiddeveloperfundamentals.clippedView

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Custom view to display clipped view
 */
class ClippedView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)
}