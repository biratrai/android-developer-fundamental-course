package com.example.gooner10.androiddeveloperfundamentals

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_keyboard.*

class KeyboardActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var phoneNumber: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyboard)
        spinnerLabel.onItemSelectedListener = this
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.labels_array, android.R.layout.simple_spinner_item)
        spinnerLabel.adapter = adapter
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Log.d("TAG", "Nothing Selected")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p0 != null) {
            Log.d("TAG", "onItemSelected: " + p0.getItemAtPosition(p2).toString())
            phoneNumber = p0.getItemAtPosition(p2).toString()
        }
        if (p1 != null) {
            showPhone(p1)
        }
    }

    fun showText(view: View) {
        if (view.id == R.id.showButton) {
            val showString = userMessageEditText.text.toString()
            if (showString.isEmpty()) {
                Toast.makeText(this, "Message is empty!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, showString, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showPhone(view: View) {
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Phone is empty!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, phoneNumber, Toast.LENGTH_SHORT).show()
        }
    }
}
