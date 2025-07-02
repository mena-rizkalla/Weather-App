package com.example.weather.weather.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrentUnits(
    val apparent_temperature: String,
    val interval: String,
    val is_day: String,
    val precipitation_probability: String,
    val pressure_msl: String,
    val relative_humidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val uv_index: String,
    val weather_code: String,
    val wind_speed_10m: String
)