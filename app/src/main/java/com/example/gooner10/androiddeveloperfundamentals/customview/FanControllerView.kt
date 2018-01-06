package com.example.gooner10.androiddeveloperfundamentals.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Custom ImageView class that shows rotation of a fan
 */
class FanControllerView : ImageView {
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

    }
}