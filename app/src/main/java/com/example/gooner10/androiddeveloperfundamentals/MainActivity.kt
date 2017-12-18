package com.example.gooner10.androiddeveloperfundamentals

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun launchKeyboard(view: View) {
        startActivity(Intent(this, KeyboardActivity::class.java))
    }

    fun launchToast(view: View) {
        startActivity(Intent(this, ToastActivity::class.java))
    }
}
