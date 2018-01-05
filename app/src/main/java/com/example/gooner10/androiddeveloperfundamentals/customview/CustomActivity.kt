package com.example.gooner10.androiddeveloperfundamentals.customview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.gooner10.androiddeveloperfundamentals.R

class CustomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom)
    }
}
