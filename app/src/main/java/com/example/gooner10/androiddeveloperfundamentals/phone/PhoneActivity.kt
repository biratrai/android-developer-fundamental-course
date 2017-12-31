package com.example.gooner10.androiddeveloperfundamentals.phone

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.telephony.PhoneNumberUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.gooner10.androiddeveloperfundamentals.R
import kotlinx.android.synthetic.main.activity_phone.*
import java.util.*

class PhoneActivity : AppCompatActivity() {
    private val TAG = PhoneActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)
    }

    fun dialPhone(view: View) {
        val phoneNumber = phone_number_editText.text.toString()

        if (phoneNumber.isNotBlank()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                PhoneNumberUtils.normalizeNumber(phoneNumber)
                PhoneNumberUtils.formatNumber(phoneNumber, Locale.getDefault().country)

                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + phoneNumber)
                Log.d(TAG, "" + intent.resolveActivity(packageManager))
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "No Activity to Dial", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Phone Number is empty", Toast.LENGTH_SHORT).show()
        }
    }
}
