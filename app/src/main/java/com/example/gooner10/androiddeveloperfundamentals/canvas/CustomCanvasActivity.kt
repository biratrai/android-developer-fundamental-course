package com.example.gooner10.androiddeveloperfundamentals.canvas

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN

class CustomCanvasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val drawCanvasView = DrawCanvasView(this)
        drawCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(drawCanvasView)
    }
}
