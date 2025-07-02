package com.example.weather.weather.presentation.weatherScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.core.domain.onError
import com.example.weather.core.domain.onSuccess
import com.example.weather.weather.domain.LocationTracker
import com.example.weather.weather.domain.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherState())
    val uiState = _uiState.asStateFlow()


    fun fetchWeatherData() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val location = locationTracker.getCurrentLocation()

            if (location != null) {
                // We have a location, fetch weather data
                repository.getWeatherData(location.latitude, location.longitude)
                    .onSuccess { weatherData ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                weather = weatherData,
                                isDarkTheme = !weatherData.currentWeather.isDay
                            )
                        }
                    }
                    .onError { error ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = error.name
                            )
                        }
                    }
            } else {
                // Location is null - likely permissions denied or GPS off
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                    )
                }
            }
        }
    }
}
