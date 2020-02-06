package com.bantulabtech.active
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mGPSCoordinatesList = ArrayList<Location>()
    private var mPreferences: SharedPreferences? = null
    private var mMapPreferences: Map<String,*> = mapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mPreferences = getSharedPreferences(Constants.SHARED_PREF_FILE, Context.MODE_PRIVATE)

        fab.setOnClickListener {
            val intent = Intent(this, NewGeofenceActivity::class.java)
            startActivity(intent)
        }
        //Load all saved GPS Coordinates
        if(mPreferences != null) {
            mMapPreferences = this.mPreferences!!.all
            mMapPreferences.forEach{
                    (value)->
                run {
                    var gson = Gson()
                    var obj = gson.fromJson<Location>(value, Location::class.java)
                    mGPSCoordinatesList.add(obj)
                }
            }
        }
        for (location in mGPSCoordinatesList){
            Log.i("GPS","Latitude: "+location.latitude)
            Log.i("GPS","Longitude: "+location.longitude)
            Log.i("GPS","Latitude: "+location.accuracy)
            Log.i("GPS","----------------------")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
