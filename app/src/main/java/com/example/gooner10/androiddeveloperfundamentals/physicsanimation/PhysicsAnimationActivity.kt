package com.example.gooner10.androiddeveloperfundamentals.physicsanimation

import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.FlingAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
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
        val flingAnimation = FlingAnimation(animationText, DynamicAnimation.X)
        flingAnimation.setStartVelocity(500f)
        flingAnimation.friction = 0.5f
        flingAnimation.start()
        springAnimation()
        return super.onTouchEvent(event)
    }

    private fun springAnimation() {
        val springAnimation = SpringAnimation(animationText, DynamicAnimation.X)
        val springForce = SpringForce()
        springForce.finalPosition = animationText.x
        springForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
        springForce.stiffness = SpringForce.STIFFNESS_LOW
        springAnimation.spring = springForce
        springAnimation.setStartVelocity(2000f)
        springAnimation.start()
    }
}

