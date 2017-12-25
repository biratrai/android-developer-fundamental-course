package com.example.gooner10.androiddeveloperfundamentals.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {

    val ACTION_USER_ALARM = "com.example.gooner10.USER_ALARM"
    val TAG = AlarmReceiver::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {
        if (ACTION_USER_ALARM.equals(intent.action)) {
            Log.d(TAG, "Received intent")
        }
    }
}
