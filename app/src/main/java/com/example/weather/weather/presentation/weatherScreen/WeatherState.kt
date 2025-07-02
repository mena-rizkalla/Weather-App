package com.example.weather.weather.presentation.weatherScreen

import com.example.weather.weather.presentation.model.WeatherUI

data class WeatherState(
    val weather: WeatherUI? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isDarkTheme: Boolean = false
)