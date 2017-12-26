package com.example.gooner10.androiddeveloperfundamentals.jobScheduler

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.example.gooner10.androiddeveloperfundamentals.R

/**
 * JobService that schedules notification when conditions are met.
 */
class NotificationJobService : JobService() {
    private val REQUEST_CODE: Int = 2017
    private val NOTIFY_ID: Int = 2074

    override fun onStopJob(jobParameters: JobParameters?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartJob(jobParameters: JobParameters?): Boolean {
        val intent: Intent = Intent(this, JobActivity::class.java)
        var pendingIntent: PendingIntent = PendingIntent.getActivity(this,
                REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        var notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this)
                .setContentTitle("Job Service")
                .setContentText("Running a Job Service")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.abc_ic_voice_search_api_material)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)

        notificationManager.notify(NOTIFY_ID, notificationBuilder.build())

        return false
    }
}