package com.example.weather.weather.data.networking

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weather.core.data.networking.safeCall
import com.example.weather.core.domain.NetworkError
import com.example.weather.core.domain.map
import com.example.weather.weather.data.mappers.WeatherMapper
import com.example.weather.weather.domain.WeatherApiService
import com.example.weather.weather.domain.model.Weather
import com.example.weather.weather.presentation.model.WeatherUI
import io.ktor.client.HttpClient
import com.example.weather.core.domain.Result
import com.example.weather.weather.domain.ReverseGeocoder
import io.ktor.client.request.get

class WeatherApiServiceImpl(
    private val client: HttpClient,
    private val reverseGeocoder: ReverseGeocoder
) : WeatherApiService {


    private val baseUrl = "https://api.open-meteo.com/v1/forecast"
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(latitude: Double, longitude: Double): Result<WeatherUI,NetworkError> {

        val addressLine = reverseGeocoder.getAddress(latitude, longitude)

        return safeCall<Weather> {
            client.get(baseUrl) {
                url {
                    parameters.append("latitude", latitude.toString())
                    parameters.append("longitude", longitude.toString())
                    parameters.append("current", "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation_probability,weather_code,pressure_msl,wind_speed_10m,uv_index")
                    parameters.append("hourly", "temperature_2m,weather_code,precipitation_probability")
                    parameters.append("daily", "weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max")
                    parameters.append("wind_speed_unit", "kmh")
                    parameters.append("timezone", "auto")
                }
            }
        }.map { weather -> WeatherMapper().mapToUI(weather, addressLine) }
//
//        try {
//            val response = client.get(baseUrl) {
//                url {
//                    parameters.append("latitude", latitude.toString())
//                    parameters.append("longitude", longitude.toString())
//                    parameters.append("current", "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation_probability,weather_code,pressure_msl,wind_speed_10m,uv_index")
//                    parameters.append("hourly", "temperature_2m,weather_code,precipitation_probability")
//                    parameters.append("daily", "weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max")
//                    parameters.append("wind_speed_unit", "kmh")
//                    parameters.append("timezone", "auto")
//                }
//            }
//
//            Log.d("WeatherApiService", "2. API Response received successfully. Status: ${response.status}")
//            Log.d("WeatherApiService", "3. Attempting to deserialize response body...")
//
//            val weatherData = response.body<Weather>()
//
//            Log.d("WeatherApiService", "4. SUCCESS! Deserialization complete.")
//            return WeatherMapper().mapToUI(weatherData, "Baghdad, Iraq")
//        }catch (e: Exception) {
//            // THIS WILL CATCH THE HIDDEN ERROR AND PRINT IT
//            Log.e("WeatherApiService", "CRITICAL_ERROR: Failed to fetch or parse weather data.", e)
//            return null // Return null because an error occurred
//        }
    }
}