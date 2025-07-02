package com.example.weather.weather.presentation.weatherScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.ui.theme.DarkNightBlue
import com.example.weather.ui.theme.LightSkyBlue
import com.example.weather.weather.presentation.weatherScreen.components.CurrentWeatherDetails
import com.example.weather.weather.presentation.weatherScreen.components.DailyForecastItem
import com.example.weather.weather.presentation.weatherScreen.components.HeaderSection
import com.example.weather.weather.presentation.weatherScreen.components.HourlyForecastItem
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel
import java.security.Permission


@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = koinViewModel()
) {

    val locationPermissionState = rememberPermissionState(
        permission = android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(key1 = locationPermissionState.status) {
        if (locationPermissionState.status != PermissionStatus.Granted) {
            // If we don't have permission, request it
            locationPermissionState.launchPermissionRequest()
        } else {
            // If we have permission, load the weather info
            viewModel.fetchWeatherData()
        }
    }

    val uiState by viewModel.uiState.collectAsState()
    val weather = uiState.weather

    val lightBrush = Brush.verticalGradient(
        colors = listOf(LightSkyBlue, Color.White)
    )
    val darkBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF060414), Color(0xFF0E0C19)) // A slightly lighter dark blue for the gradient
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = if (uiState.isDarkTheme) darkBrush else lightBrush
//                Brush.verticalGradient(
//                    colors = listOf(
//                        Color(0xFF8BCEF7),
//                        Color(0x00000000)
//                    )
                )
    ) {
        if (locationPermissionState.status != PermissionStatus.Granted) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Location permission is required for this app.",
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { locationPermissionState.launchPermissionRequest() }) {
                    Text("Grant Permission")
                }
            }
        } else {

            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.errorMessage != null) {
                Text(
                    text = uiState.errorMessage ?: "An error occurred",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (weather != null) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    item { HeaderSection(weather) }

                    item { CurrentWeatherDetails(weather.currentWeather) }

                    item {
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {

                            Text(
                                text = "Today",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 0.25.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            LazyRow {
                                items(weather.todayForecast) { hourlyForecast ->
                                    HourlyForecastItem(
                                        forecast = hourlyForecast,
                                        isDarkTheme = uiState.isDarkTheme
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Next 7 days",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    itemsIndexed(weather.weeklyForecast) { index, dailyForecast ->
                        val shape = when (index) {
                            0 -> RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                            weather.weeklyForecast.lastIndex -> RoundedCornerShape(
                                bottomStart = 20.dp,
                                bottomEnd = 20.dp
                            )

                            else -> RectangleShape
                        }
                        DailyForecastItem(forecast = dailyForecast, shape = shape, isDarkTheme = uiState.isDarkTheme)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {

}