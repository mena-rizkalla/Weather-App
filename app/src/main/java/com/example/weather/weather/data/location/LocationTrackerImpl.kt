package com.example.weather.weather.data.location

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.weather.weather.domain.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class LocationTrackerImpl(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {

    override suspend fun getCurrentLocation(): Location? {
        // 1. Check for fine location permission
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        // 2. Check for coarse location permission
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        // 3. Check if GPS is enabled
        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        // Return null if permissions are not granted or GPS is disabled
        if (!hasAccessFineLocationPermission || !hasAccessCoarseLocationPermission || !isGpsEnabled) {
            return null
        }

        // 4. Use suspendCancellableCoroutine to await the location result
        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.addOnSuccessListener { location ->
                // In some cases lastLocation can be null, so we request a fresh one
                if (location == null) {
                    // This is a more modern way to get the current location
                    locationClient.getCurrentLocation(
                        com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
                        null
                    ).addOnSuccessListener { freshLocation ->
                        cont.resume(freshLocation)
                    }.addOnFailureListener {
                        cont.resume(null)
                    }
                } else {
                    cont.resume(location)
                }
            }.addOnFailureListener {
                cont.resume(null)
            }
        }
    }
}