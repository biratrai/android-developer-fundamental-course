package com.example.gooner10.androiddeveloperfundamentals.jobScheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.gooner10.androiddeveloperfundamentals.R

/**
 * Activity to handle JobScheduler API
 */
class JobActivity : AppCompatActivity() {
    private var jobScheduler: JobScheduler? = null
    private val JOB_ID: Int = 1080

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun startJob(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler?

            val serviceName = ComponentName(packageName,
                    NotificationJobService::class.java.name)
            val builder = JobInfo.Builder(JOB_ID, serviceName)
                    .setRequiresCharging(true)

            val notificationJob = builder.build()
            jobScheduler?.schedule(notificationJob)
            Toast.makeText(this, "Job Scheduled", Toast.LENGTH_SHORT).show()
        }
    }

    fun cancelJob(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            jobScheduler?.cancelAll()
            Toast.makeText(this, "Jobs Canceled", Toast.LENGTH_SHORT).show()
        }
    }
}
