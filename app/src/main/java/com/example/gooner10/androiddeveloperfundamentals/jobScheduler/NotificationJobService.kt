package com.example.gooner10.androiddeveloperfundamentals.jobScheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.example.gooner10.androiddeveloperfundamentals.R


/**
 * JobService that schedules notification when conditions are met.
 */
class NotificationJobService : JobService() {
    private val REQUEST_CODE: Int = 2017
    private val NOTIFY_ID: Int = 2017
    private val TAG = NotificationJobService::class.java.simpleName
    private val NOTIFICATION_CHANNEL_ID = "my_notification_channel"

    override fun onStopJob(jobParameters: JobParameters?): Boolean {
        Log.d(TAG, "Job Stopped")
        return true
    }

    override fun onStartJob(jobParameters: JobParameters?): Boolean {
        Log.d(TAG, "Job Started")
        val intent = Intent(this, JobActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this,
                REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT)

            // Configure the notification channel.
            notificationChannel.description = "Channel description"
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Job Service")
                .setContentText("Running a Job Service")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)

        notificationManager.notify(NOTIFY_ID, notificationBuilder.build())

        return false
    }
}