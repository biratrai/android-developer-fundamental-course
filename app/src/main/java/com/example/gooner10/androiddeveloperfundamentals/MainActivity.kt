package com.example.gooner10.androiddeveloperfundamentals

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * When the button is clicked, it shows toast
     */
    fun showToast(view: View) {
        Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ScrollActivity::class.java)
        startActivity(intent)
    }

    /**
     * Increases the count in text, when the button is clicked
     */
    fun countUp(view: View) {
        countTextView.text = Integer.toString(count++)
    }
}
