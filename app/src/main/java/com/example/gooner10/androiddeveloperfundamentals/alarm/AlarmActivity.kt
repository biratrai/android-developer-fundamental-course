package com.example.gooner10.androiddeveloperfundamentals.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.gooner10.androiddeveloperfundamentals.R

class AlarmActivity : AppCompatActivity() {
    private val ALARM_REQUEST_CODE = 2017
    private val TAG = AlarmActivity::class.java.simpleName

    companion object {
        const val ALARM_DATA = "data"
        const val ACTION_USER_ALARM = "com.example.gooner10.USER_ALARM"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
    }

    fun startAlarmManager(view: View) {
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.action = ACTION_USER_ALARM
        intent.putExtra(ALARM_DATA, "From AlarmActivity")

        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val currentTimeInMilliSeconds = SystemClock.elapsedRealtime()
        val SECONDS = 6 * 1000L
        Log.d(TAG, "currentTimeInMilliSeconds: " + currentTimeInMilliSeconds)
        var alarmTime = currentTimeInMilliSeconds + SECONDS
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                alarmTime,
                SECONDS
                , pendingIntent)
    }
}
