package com.example.gooner10.androiddeveloperfundamentals.clippedView

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ClippedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ClippedView(this))
    }
}
