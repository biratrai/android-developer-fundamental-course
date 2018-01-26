package com.example.gooner10.androiddeveloperfundamentals.physicsanimation

import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.FlingAnimation
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
        return super.onTouchEvent(event)
    }
}

