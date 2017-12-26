package com.example.gooner10.androiddeveloperfundamentals.jobScheduler

import android.app.job.JobScheduler
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.gooner10.androiddeveloperfundamentals.R

/**
 * Activity to handle JobScheduler API
 */
class JobActivity : AppCompatActivity() {
    private var jobScheduler: JobScheduler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)

    }

    fun startJob(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler?

        }

    }

    fun cancelJob(view: View) {

    }
}
