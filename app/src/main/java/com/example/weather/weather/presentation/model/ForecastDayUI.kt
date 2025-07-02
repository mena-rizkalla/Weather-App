package com.example.weather.weather.presentation.model


data class ForecastDayUI(
    val dayName: String,              // e.g., "Monday"
    val maxTemp: String,              // e.g., "↑ 32°C"
    val minTemp: String,              // e.g., "↓ 20°C"
    val weatherCode: String
)

