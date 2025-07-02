package com.example.weather.weather.domain

import com.example.weather.core.domain.NetworkError
import com.example.weather.core.domain.Result
import com.example.weather.weather.presentation.model.WeatherUI

interface WeatherApiService {
    suspend fun getWeatherData(latitude: Double, longitude: Double): Result<WeatherUI, NetworkError>
}