package com.example.gooner10.androiddeveloperfundamentals

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.gooner10.androiddeveloperfundamentals.alarm.AlarmActivity
import com.example.gooner10.androiddeveloperfundamentals.backgroundTask.AsyncLoaderActivity
import com.example.gooner10.androiddeveloperfundamentals.contentProvider.ContentProviderActivity
import com.example.gooner10.androiddeveloperfundamentals.database.DatabaseActivity
import com.example.gooner10.androiddeveloperfundamentals.jobScheduler.JobActivity
import com.example.gooner10.androiddeveloperfundamentals.rxKotlin.RxActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun launchKeyboard(view: View) {
        startActivity(Intent(this, KeyboardActivity::class.java))
    }

    fun launchToast(view: View) {
        startActivity(Intent(this, ToastActivity::class.java))
    }

    fun launchRecyclerView(view: View) {
        startActivity(Intent(this, RecyclerViewActivity::class.java))
    }

    fun launchLoader(view: View) {
        startActivity(Intent(this, AsyncLoaderActivity::class.java))
    }

    fun launchRx(view: View) {
        startActivity(Intent(this, RxActivity::class.java))
    }

    fun launchDatabaseActivity(view: View) {
        startActivity(Intent(this, DatabaseActivity::class.java))
    }

    fun launchAlarmActivity(view: View) {
        startActivity(Intent(this, AlarmActivity::class.java))
    }

    fun launchJobActivity(view: View) {
        startActivity(Intent(this, JobActivity::class.java))
    }

    fun launchProviderActivity(view: View) {
        startActivity(Intent(this, ContentProviderActivity::class.java))
    }
}
