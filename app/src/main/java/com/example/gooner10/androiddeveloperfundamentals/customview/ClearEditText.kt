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
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.gooner10.androiddeveloperfundamentals.R


/**
 * Custom EditText with Clear buttons
 */

class ClearEditText : AppCompatEditText {
    private var clearButtonImage: Drawable? = null
    private var TAG = ClearEditText::class.java.simpleName

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
                R.drawable.abc_ic_clear_material, null)

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
                    val clearButtonStart: Int // Used for LTR languages
                    val clearButtonEnd: Int  // Used for RTL languages
                    var isClearButtonClicked = false
                    // TODO: Detect the touch in RTL or LTR layout direction.
                    // TODO: Check for actions if the button is tapped.
                    if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                        Log.d(TAG, "LAYOUT_DIRECTION_RTL")
                        clearButtonEnd = clearButtonImage!!.intrinsicWidth + paddingStart
                        // If the touch occurred before the end of the button,
                        // set isClearButtonClicked to true.
                        if (event!!.x < clearButtonEnd) {
                            isClearButtonClicked = true
                        }
                    } else if (layoutDirection == View.LAYOUT_DIRECTION_LTR) {
                        Log.d(TAG, "LAYOUT_DIRECTION_LTR")
                        clearButtonStart = ((width - paddingEnd) - clearButtonImage!!.intrinsicWidth)
                        if (event!!.x > clearButtonStart) {
                            isClearButtonClicked = true
                            Log.d(TAG, "Clear button is clicked")
                        } else {
                            Log.d(TAG, "Clear button not clicked")
                        }
                    }
                    // TODO: If the text changes, show or hide the clear (X) button.
                    if (isClearButtonClicked) {
                        if (event!!.action == MotionEvent.ACTION_DOWN) {
                            clearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_clear_black_24dp, null)
                            showClearButton()
                        }

                        if (event.action == MotionEvent.ACTION_UP) {
                            clearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.abc_ic_clear_material, null)
                            text.clear()
                            hideClearButton()
                            return true
                        }
                    } else {
                        return false
                    }
                }

                return false
            }

        })
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
