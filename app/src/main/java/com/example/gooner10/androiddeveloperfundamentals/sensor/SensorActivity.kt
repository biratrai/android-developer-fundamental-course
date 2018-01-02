package com.example.gooner10.androiddeveloperfundamentals.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.gooner10.androiddeveloperfundamentals.R
import kotlinx.android.synthetic.main.activity_sensor.*

class SensorActivity : AppCompatActivity() {
    private var sensorManager: SensorManager? = null
    private val TAG = SensorActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?

        val sensorList = sensorManager!!.getSensorList(Sensor.TYPE_ALL)
        var sensorText = ""
        for (currentSenor in sensorList) {
            sensorText += "${currentSenor.name} $\n"
//            sensorText.plus("\n")

        }
        Log.d(TAG, "currentSenor: " + sensorText)
        sensor_list.text = sensorText
    }
}
