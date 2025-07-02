package com.example.weather.weather.presentation.model

data class CurrentWeatherUI(
    val temperature: String,          // e.g., "24°C"
    val conditionText: String,        // e.g., "Partly cloudy"
    val dailyHigh: String,            // e.g., "↑ 32°C"
    val dailyLow: String,             // e.g., "↓ 20°C"
    val windSpeed: String,            // e.g., "13 km/h"
    val humidity: String,             // e.g., "24%"
    val precipitationProbability: String, // e.g., "2%"
    val uvIndex: String,              // e.g., "2"
    val pressure: String,             // e.g., "1012 hPa"
    val feelsLike: String,            // e.g., "22°C"
    val weatherCode: Int,             // The raw WMO code for showing the correct icon
    val isDay: Boolean
)

