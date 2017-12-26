package com.example.gooner10.androiddeveloperfundamentals.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.gooner10.androiddeveloperfundamentals.alarm.AlarmActivity.Companion.ACTION_USER_ALARM
import com.example.gooner10.androiddeveloperfundamentals.alarm.AlarmActivity.Companion.ALARM_DATA

class AlarmReceiver : BroadcastReceiver() {

    val TAG = AlarmReceiver::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {
        if (ACTION_USER_ALARM.equals(intent.action)) {
            Log.d(TAG, "Received intent -> " + intent.getStringExtra(ALARM_DATA))
        }
    }
}
