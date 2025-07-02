package com.example.weather.weather.presentation.weatherScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.weather.presentation.model.CurrentWeatherUI

@Composable
fun CurrentWeatherDetails(currentWeather: CurrentWeatherUI) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            InfoCard(Modifier.weight(1f), currentWeather.windSpeed, "Wind", R.drawable.fast_wind)
            InfoCard(Modifier.weight(1f), currentWeather.humidity, "Humidity", R.drawable.humidity)
            InfoCard(Modifier.weight(1f), currentWeather.precipitationProbability, "Rain", R.drawable.rain)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfoCard(Modifier.weight(1f), currentWeather.uvIndex, "UV Index", R.drawable.uv_02)
            InfoCard(Modifier.weight(1f), currentWeather.pressure, "Pressure", R.drawable.arrow_down_05)
            InfoCard(Modifier.weight(1f), currentWeather.feelsLike, "Feels Like", R.drawable.temperature)
        }
    }
}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    weatherState: String,
    text: String,
    image: Int
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Icon(
                painter = painterResource(id = image),
                contentDescription = "Fast Wind",
                tint = Color(0xFF87CEFA)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = weatherState,
                fontWeight = FontWeight.Medium,
                fontSize = 19.sp,
                letterSpacing = 0.25.sp,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = text,
                fontSize = 14.sp,
                letterSpacing = 0.25.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}