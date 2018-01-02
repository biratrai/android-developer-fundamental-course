package com.example.gooner10.androiddeveloperfundamentals.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.gooner10.androiddeveloperfundamentals.R
import kotlinx.android.synthetic.main.activity_sensor.*

class SensorActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private val TAG = SensorActivity::class.java.simpleName
    private var sensorLight: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?

        val sensorList = sensorManager!!.getSensorList(Sensor.TYPE_ALL)
        var sensorText = ""
        for (currentSenor in sensorList) {
            sensorText += "${currentSenor.name} \n"

        }
        Log.d(TAG, "currentSenor: " + sensorText)
        sensor_list.text = sensorText

        sensorLight = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)

    }

    override fun onStart() {
        super.onStart()
        sensorManager?.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onStop() {
        super.onStop()
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d(TAG, "sensor: " + sensor + accuracy)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        Log.d(TAG, "event: " + event)
        when (event!!.sensor.type) {
            Sensor.TYPE_LIGHT -> Log.d(TAG, "TYPE_LIGHT")

            else -> Log.d(TAG, "Not useful sensor")

        }
    }
}
