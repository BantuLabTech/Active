package com.bantulabtech.active

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.bantulabtech.active.Constants.PERMISSION_ID
import com.bantulabtech.active.Constants.SHARED_PREF_FILE
import com.bantulabtech.active.databinding.ActivityNewGeofenceBinding
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.util.*

class NewGeofenceActivity : AppCompatActivity() {
    private var mPreferences: SharedPreferences? = null
    private var mGPSCoordinates: Location? = null
    private var GPS_ENTRY_KEY: String? = null
    lateinit var binding: ActivityNewGeofenceBinding
    lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    lateinit var classRoomModel: ClassRoom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_geofence)

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        mPreferences = getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)

        classRoomModel = ClassRoom(
            _updating = false,
            _gpsAltitude = "none",
            _gpsLatitude = "none",
            _gpsLongitude = "none",
            _gpsAccuracy = "none",
            _gpsSpeed = "none",
            _gpsProvider = "none"
        )
        binding.classRoom = classRoomModel

        binding.btnRecordGPS.setOnClickListener {
            classRoomModel.updating = true
            requestNewLocationData()
        }
        binding.btnSaveGPS.setOnClickListener {
            saveGPSCoordinates()
        }
    }

    override fun onPause() {
        super.onPause()
        removeLocationUpdates()
    }

    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationProviderClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        updateLocationUI(location)
                    }
                }

            } else {
                Log.i("GPS", "Location Services is OFF!")
                Snackbar.make(
                    findViewById(R.id.btnSaveGPS),
                    "Turn On Location Services",
                    Snackbar.LENGTH_LONG
                ).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            Log.i("GPS", "Requesting Location Permissions")
            requestPermissions()
        }
    }

    private fun requestNewLocationData() {
        classRoomModel.updating=true
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 1000
        mLocationRequest.fastestInterval = 3000
        mLocationRequest.numUpdates = 1

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationProviderClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            updateLocationUI(mLastLocation)
        }
    }

    private fun updateLocationUI(lastLocation: Location) {
        classRoomModel.updating = false
        classRoomModel.gpsAccuracy = lastLocation.accuracy.toString()
        classRoomModel.gpsAltitude = lastLocation.altitude.toString()
        classRoomModel.gpsLongitude = lastLocation.longitude.toString()
        classRoomModel.gpsLatitude = lastLocation.latitude.toString()
        classRoomModel.gpsSpeed = lastLocation.speed.toString()
        classRoomModel.gpsProvider = lastLocation.provider.toString()
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        return true
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ), PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }

    private fun removeLocationUpdates() {
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback)
    }

    fun saveGPSCoordinates() {
        classRoomModel.updating = false
        if (mGPSCoordinates == null) {
        } else {
            var preferencesEditor: SharedPreferences.Editor = mPreferences!!.edit()
            var gson = Gson()
            var json = gson.toJson(mGPSCoordinates)
            GPS_ENTRY_KEY = UUID.randomUUID().toString()
            preferencesEditor.putString(GPS_ENTRY_KEY, json)
            preferencesEditor.commit()
        }
    }
}
