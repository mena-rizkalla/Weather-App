package com.example.weather.weather.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Current(
    val apparent_temperature: Double,
    val interval: Int,
    val is_day: Int,
    val precipitation_probability: Int,
    val pressure_msl: Double,
    val relative_humidity_2m: Int,
    val temperature_2m: Double,
    val time: String,
    val uv_index: Double? = null,
    val weather_code: Int,
    val wind_speed_10m: Double
)