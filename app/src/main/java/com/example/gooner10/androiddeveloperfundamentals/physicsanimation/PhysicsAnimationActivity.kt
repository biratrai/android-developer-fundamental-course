package com.example.gooner10.androiddeveloperfundamentals.physicsanimation

import android.os.Bundle
import android.support.animation.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.gooner10.androiddeveloperfundamentals.R
import kotlinx.android.synthetic.main.activity_physics_animation.*
import org.jetbrains.anko.contentView


class PhysicsAnimationActivity : AppCompatActivity(), View.OnTouchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics_animation)
        contentView?.setOnTouchListener(this)

    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        Log.d("TAG", "onTouch Clicked")

        return super.onTouchEvent(event)
    }

    fun flingMe(view: View) {
        val flingAnimation = FlingAnimation(androidImage, DynamicAnimation.X)
        flingAnimation.setStartVelocity(500f)
        flingAnimation.friction = 0.5f
        flingAnimation.start()
    }

    fun springMe(view: View) {
        val springAnimation = SpringAnimation(androidImage, DynamicAnimation.X)
        val springForce = SpringForce()
        springForce.finalPosition = androidImage.x
        springForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
        springForce.stiffness = SpringForce.STIFFNESS_LOW
        springAnimation.spring = springForce
        springAnimation.setStartVelocity(2000f)
        springAnimation.start()
        springAnimation.addEndListener { animation, canceled, value, velocity ->
            // Change icon after animation ends
            androidImage.setImageResource(
                    R.drawable.ic_android_black_24dp)
        }
    }

    fun floatAndStretch(view: View) {
        val scale = object : FloatPropertyCompat<View>("scale") {
            override fun getValue(view: View): Float {
                // return the value of any one property
                return view.scaleX
            }

            override fun setValue(view: View, value: Float) {
                // Apply the same value to two properties
                view.scaleX = value/2
                view.scaleY = value/2
            }
        }

        val springAnimation = SpringAnimation(androidImage, scale)
        val springForce = SpringForce()
        springForce.finalPosition = androidImage.x
        springForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
        springForce.stiffness = SpringForce.STIFFNESS_LOW
        springAnimation.spring = springForce
        springAnimation.setStartVelocity(2000f)
        springAnimation.minimumVisibleChange = DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE
        springAnimation.start()
//        springAnimation.addEndListener { animation, canceled, value, velocity ->
//            // Change icon after animation ends
//            androidImage.setImageResource(
//                    R.drawable.ic_android_black_24dp)
//        }
    }
}

