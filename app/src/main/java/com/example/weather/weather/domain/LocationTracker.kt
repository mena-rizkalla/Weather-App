package com.example.weather.weather.domain

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}