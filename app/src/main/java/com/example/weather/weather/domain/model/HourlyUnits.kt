package com.example.weather.weather.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class HourlyUnits(
    val precipitation_probability: String,
    val temperature_2m: String,
    val time: String,
    val weather_code: String
)