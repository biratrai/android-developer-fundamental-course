package com.example.gooner10.androiddeveloperfundamentals.geofeature

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.gooner10.androiddeveloperfundamentals.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class GeoActivity : AppCompatActivity() {
    private val REQUEST_LOCATION_PERMISSION: Int = 1001
    private val TAG = GeoActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo)
    }

    @SuppressWarnings
    fun getLocation(view: View) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        } else {
            Log.d(TAG, "getLocation: permission granted")
            getLastUserLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission was granted.", Toast.LENGTH_SHORT).show()
                    getLastUserLocation()
                }
            }
            else -> Toast.makeText(this, "Permission was denied.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getLastUserLocation() {
        val fusedLocationProvideClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProvideClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    Log.d(Geo.TAG, "onSuccess: ")
                } else {
                    Log.d("TAG", "No location")
                }
            }
        }


    }
}
