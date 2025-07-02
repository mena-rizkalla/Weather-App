package com.example.weather.weather.data.location

import android.app.Application
import android.location.Geocoder
import com.example.weather.weather.domain.ReverseGeocoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class AndroidReverseGeocoder(
    private val application: Application
) : ReverseGeocoder {

    // Instantiate the Geocoder lazily
    private val geocoder by lazy { Geocoder(application) }

    override suspend fun getAddress(latitude: Double, longitude: Double): String {
        return withContext(Dispatchers.IO) {
            try {
                // The getFromLocation method can sometimes be slow or fail.
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)

                // If we get an address, format it nicely.
                if (addresses?.isNotEmpty() == true) {
                    val address = addresses[0]
                    // Combine city and country for a clean location string
                    val city = address.locality ?: address.subAdminArea ?: "Unknown City"
                    val country = address.countryName ?: "Unknown Country"
                    "$city, $country"
                } else {
                    // Fallback if no address is found
                    "Current Location"
                }
            } catch (e: IOException) {
                // Handle network errors or other issues
                "Current Location"
            }
        }
    }
}