package com.authentixlib.helper

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.authentixlib.LSXCommandWrapper
import com.authentixlib.log.LSXLog
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task

internal object LocationHelper
{
    private val TAG = this.javaClass.name
    private var cancellationTokenSource = CancellationTokenSource()
    private var mLocation : Location? = null

    //Setter Getter
    fun getLocation(): Location?
    {
        return mLocation
    }
    private fun setLocation(mLocation: Location)
    {
        this.mLocation = mLocation
    }

    fun getCurrentLocation()
    {
        val activity = LSXCommandWrapper.getInstance()?.getActivityContext()!!

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return
        }

        val currentLocationTask: Task<Location> =  fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)

        currentLocationTask.addOnCompleteListener { task: Task<Location> ->
            if (task.isSuccessful && task.result != null)
            {
                val result: Location = task.result
                setLocation(result)
                LSXLog.i(TAG,"getLocation : (success): ${result.latitude}, ${result.longitude}")
            }
            else
            {
                val exception = task.exception
                LSXLog.i(TAG,"getLocation : "+exception?.message.toString())
            }
        }
    }
}