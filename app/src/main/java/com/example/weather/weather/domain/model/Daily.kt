package com.example.weather.weather.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Daily(
    val precipitation_probability_max: List<Int>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<String>,
    val weather_code: List<Int>
)