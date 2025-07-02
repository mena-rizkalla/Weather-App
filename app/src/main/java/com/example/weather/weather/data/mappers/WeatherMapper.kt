package com.example.weather.weather.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weather.core.presentation.WeatherCodeUtil
import com.example.weather.weather.domain.model.Current
import com.example.weather.weather.domain.model.CurrentUnits
import com.example.weather.weather.domain.model.Daily
import com.example.weather.weather.domain.model.DailyUnits
import com.example.weather.weather.domain.model.Hourly
import com.example.weather.weather.domain.model.HourlyUnits
import com.example.weather.weather.domain.model.Weather
import com.example.weather.weather.presentation.model.CurrentWeatherUI
import com.example.weather.weather.presentation.model.ForecastDayUI
import com.example.weather.weather.presentation.model.HourlyForecastUI
import com.example.weather.weather.presentation.model.WeatherUI
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeatherMapper {

    @RequiresApi(Build.VERSION_CODES.O)
    fun mapToUI(weatherData: Weather, location: String): WeatherUI {
        return WeatherUI(
            location = location,
            currentWeather = mapToCurrentWeatherUI(weatherData.current, weatherData.current_units, weatherData.daily),
            todayForecast = mapToHourlyForecastUI(weatherData.hourly, weatherData.hourly_units),
            weeklyForecast = mapToDailyForecastUI(weatherData.daily, weatherData.daily_units)
        )
    }

    private fun mapToCurrentWeatherUI(current: Current, units: CurrentUnits, daily: Daily): CurrentWeatherUI {
        return CurrentWeatherUI(
            temperature = "${current.temperature_2m.toInt()}${units.temperature_2m}",
            conditionText = WeatherCodeUtil.toText(current.weather_code),
            weatherCode = current.weather_code,
            isDay = current.is_day == 1,
            // Today's high/low comes from the first entry in the daily forecast
            dailyHigh = "${daily.temperature_2m_max.firstOrNull()?.toInt()}${units.temperature_2m}",
            dailyLow = "${daily.temperature_2m_min.firstOrNull()?.toInt()}${units.temperature_2m}",
            windSpeed = "${current.wind_speed_10m} ${units.wind_speed_10m}",
            humidity = "${current.relative_humidity_2m}${units.relative_humidity_2m}",
            precipitationProbability = "${current.precipitation_probability}${units.precipitation_probability}",
            uvIndex = current.uv_index?.toInt().toString(),
            pressure = "${current.pressure_msl.toInt()} ${units.pressure_msl}",
            feelsLike = "${current.apparent_temperature.toInt()}${units.apparent_temperature}"
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun mapToHourlyForecastUI(hourly: Hourly, units: HourlyUnits): List<HourlyForecastUI> {
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        // The API returns parallel lists. We combine them based on their index.
        return hourly.time.mapIndexedNotNull { index, timeString ->
            // You can add logic here to only show future hours, for example
            val time = LocalDateTime.parse(timeString)

            val temperature = hourly.temperature_2m.getOrNull(index) ?: return@mapIndexedNotNull null
            val weatherCode = hourly.weather_code.getOrNull(index) ?: return@mapIndexedNotNull null

            HourlyForecastUI(
                time = time.format(timeFormatter),
                temperature = "${temperature.toInt()}${units.temperature_2m}",
                weatherCode = weatherCode.toString()
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun mapToDailyForecastUI(daily: Daily, units: DailyUnits): List<ForecastDayUI> {
        val dayFormatter = DateTimeFormatter.ofPattern("EEEE")

        return daily.time.mapIndexedNotNull { index, dateString ->
            val date = LocalDate.parse(dateString)

            val maxTemp = daily.temperature_2m_max.getOrNull(index) ?: return@mapIndexedNotNull null
            val minTemp = daily.temperature_2m_min.getOrNull(index) ?: return@mapIndexedNotNull null
            val weatherCode = daily.weather_code.getOrNull(index) ?: return@mapIndexedNotNull null

            ForecastDayUI(
                dayName = date.format(dayFormatter),
                maxTemp = "${maxTemp.toInt()}${units.temperature_2m_max}",
                minTemp = "${minTemp.toInt()}${units.temperature_2m_min}",
                weatherCode = weatherCode.toString()
            )
        }
    }


}