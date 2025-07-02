package com.example.weather.weather.domain

interface ReverseGeocoder {
    suspend fun getAddress(latitude: Double, longitude: Double): String
}