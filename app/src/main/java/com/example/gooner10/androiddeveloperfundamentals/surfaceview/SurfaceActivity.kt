package com.example.gooner10.androiddeveloperfundamentals.surfaceview

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class SurfaceActivity : AppCompatActivity() {

    private lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        gameView = GameView(this)
        gameView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(gameView)
    }

    override fun onPause() {
        super.onPause()
        gameView.pause()
    }

    override fun onResume() {
        super.onResume()
        gameView.resume()
    }
}
