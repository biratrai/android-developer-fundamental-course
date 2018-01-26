package com.example.gooner10.androiddeveloperfundamentals.physicsanimation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.gooner10.androiddeveloperfundamentals.R
import org.jetbrains.anko.contentView

class PhysicsAnimationActivity : AppCompatActivity(), View.OnTouchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics_animation)
        contentView?.setOnTouchListener(this);
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        Log.d("TAG", "onTouch Clicked")

        return super.onTouchEvent(event);
    }
}

