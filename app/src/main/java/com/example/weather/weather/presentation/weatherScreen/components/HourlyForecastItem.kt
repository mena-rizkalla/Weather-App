package com.example.weather.weather.presentation.weatherScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.core.presentation.WeatherIconUtil
import com.example.weather.weather.presentation.model.HourlyForecastUI

@Composable
fun HourlyForecastItem(forecast: HourlyForecastUI , isDarkTheme: Boolean) {

    Box(
        modifier = Modifier
            .width(96.dp)
            .height(120.dp)
            .padding(horizontal = 6.dp)
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 58.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = forecast.temperature,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.25.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = forecast.time,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(0.7f),
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.25.sp,
            )
        }

        Image(
            painter = painterResource(
                WeatherIconUtil.getIconResource(
                    forecast.weatherCode.toInt(),
                    isDay = !isDarkTheme
                )
            ),
            contentScale = ContentScale.Fit,
            contentDescription = "Image",
            modifier = Modifier
                .size(58.dp)
                .offset(y = (-44).dp)
        )
    }
}