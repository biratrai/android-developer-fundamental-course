package com.example.gooner10.androiddeveloperfundamentals.geofeature

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.gooner10.androiddeveloperfundamentals.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_geo.*
import java.util.*
import kotlin.collections.ArrayList

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

    private fun getLastUserLocation(): Location? {
        val fusedLocationProvideClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        var userLocation: Location? = null
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProvideClient.lastLocation.addOnSuccessListener { location ->
                userLocation = location
                if (location != null) {
                    Log.d(TAG, "onSuccess: ")
                    location_textview.text = getString(R.string.location_text, location.latitude, location.longitude, location.time)
                } else {
                    Log.d("TAG", "No location")
                    location_textview.text = getString(R.string.no_location_available)
                }
                setAddress(location)
            }
        }
        return userLocation
    }

    fun setAddress(location: Location) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addressList: List<Address>
        addressList = geocoder.getFromLocation(location.latitude, location.longitude, 1)

        if (addressList.isNotEmpty()) {
            val address: Address = addressList[0]
            val addressParts: ArrayList<String> = ArrayList()
            for (i in 0..address.maxAddressLineIndex) {
                addressParts.add(address.getAddressLine(i))
            }

            address_textview.text = TextUtils.join("\n", addressParts)
        } else {
            address_textview.text = getString(R.string.address_not_found)
        }
    }
}
