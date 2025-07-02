package com.example.weather.weather.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyUnits(
    val precipitation_probability_max: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val weather_code: String
)