package com.example.gooner10.androiddeveloperfundamentals

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * When the button is clicked, it shows toast
     */
    fun showToast(view: View) {
        Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_SHORT).show();
    }

    /**
     * Increases the count in text, when the button is clicked
     */
    fun countUp(view: View) {

    }
}
