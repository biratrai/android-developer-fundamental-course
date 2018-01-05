package com.example.gooner10.androiddeveloperfundamentals.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.gooner10.androiddeveloperfundamentals.R


/**
 * Custom EditText with Clear buttons
 */

class ClearEditText : AppCompatEditText {
    private var clearButtonImage: Drawable? = null

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
        clearButtonImage = ResourcesCompat.getDrawable(resources,
                R.drawable.ic_clear_black_24dp, null)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                showClearButton()
            }

            override fun afterTextChanged(s: Editable) {}
        })

        // TODO: If the clear (X) button is tapped, clear the text.
        setOnTouchListener(object : View.OnTouchListener {
            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (compoundDrawablesRelative[2] != null) {
                    val clearButtonStart: Float // Used for LTR languages
                    val clearButtonEnd: Float  // Used for RTL languages
                    val isClearButtonClicked = false
                    // TODO: Detect the touch in RTL or LTR layout direction.
                    // TODO: Check for actions if the button is tapped.
                }
                return false
            }
        })
        // TODO: If the text changes, show or hide the clear (X) button.
    }

    private fun showClearButton() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                    null,
                    clearButtonImage,
                    null)
        }
    }

    private fun hideClearButton() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                    null,
                    null,
                    null)
        }
    }
}
