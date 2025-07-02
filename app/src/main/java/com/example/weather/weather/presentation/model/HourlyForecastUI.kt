package com.example.weather.weather.presentation.model


data class HourlyForecastUI(
    val time: String,                 // e.g., "11:00"
    val temperature: String,          // e.g., "25°C"
    val weatherCode: String
)

