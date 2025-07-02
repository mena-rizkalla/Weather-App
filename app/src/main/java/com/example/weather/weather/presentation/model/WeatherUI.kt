package com.example.weather.weather.presentation.model

data class WeatherUI(
    val location: String,
    val currentWeather: CurrentWeatherUI,
    val todayForecast: List<HourlyForecastUI>,
    val weeklyForecast: List<ForecastDayUI>
)

