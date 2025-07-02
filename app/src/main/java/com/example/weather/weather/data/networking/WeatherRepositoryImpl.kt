package com.example.weather.weather.data.networking

import com.example.weather.core.domain.NetworkError
import com.example.weather.weather.domain.WeatherApiService
import com.example.weather.weather.domain.WeatherRepository
import com.example.weather.weather.presentation.model.WeatherUI
import com.example.weather.core.domain.Result


class WeatherRepositoryImpl(
    private val apiService: WeatherApiService
) : WeatherRepository {
    override suspend fun getWeatherData(latitude: Double, longitude: Double): Result<WeatherUI, NetworkError> {
        return apiService.getWeatherData(latitude, longitude)
    }
}