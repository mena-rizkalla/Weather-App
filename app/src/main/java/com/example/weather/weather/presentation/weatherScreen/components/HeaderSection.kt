package com.example.weather.weather.presentation.weatherScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.core.presentation.WeatherIconUtil
import com.example.weather.weather.presentation.model.WeatherUI

@Composable
fun HeaderSection(weather: WeatherUI) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.location_05),
                contentDescription = "Location",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = weather.location,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 0.25.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(0.7f)
            )
        }

        Image(
            painter = painterResource(WeatherIconUtil.getIconResource(weather.currentWeather.weatherCode , weather.currentWeather.isDay)),
            contentDescription = weather.currentWeather.conditionText,
            modifier = Modifier
                .width(220.dp)
                .height(185.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = weather.currentWeather.temperature,
            fontSize = 64.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.25.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = weather.currentWeather.conditionText,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            letterSpacing = 0.25.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(0.7f)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Surface(
            modifier = Modifier
                .width(168.dp)
                .height(35.dp),
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(25.dp),
        ) {

            Row(
                Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Icon(
                    painter = painterResource(R.drawable.arrow_up_04),
                    contentDescription = "Arrow up",
                    modifier = Modifier
                        .size(12.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    text = weather.currentWeather.dailyHigh,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(Modifier.width(12.dp))
                VerticalDivider(
                    modifier = Modifier
                        .height(14.dp)
                        .width(1.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
                Spacer(Modifier.width(12.dp))


                Icon(
                    painter = painterResource(R.drawable.arrow_down_04),
                    contentDescription = "Arrow down",
                    modifier = Modifier
                        .size(12.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.width(4.dp))

                Text(
                    text = weather.currentWeather.dailyLow,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

            }

        }

    }
}