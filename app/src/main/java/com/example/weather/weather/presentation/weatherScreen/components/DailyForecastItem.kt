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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.core.presentation.WeatherIconUtil
import com.example.weather.weather.presentation.model.ForecastDayUI

@Composable
fun DailyForecastItem(
    modifier: Modifier = Modifier,
    forecast: ForecastDayUI,
    shape: Shape,
    isDarkTheme: Boolean
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = forecast.dayName,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurface.copy(0.7f),
                    fontSize = 16.sp,
                    letterSpacing = 0.25.sp
                )

                Image(
                    painter = painterResource(
                        WeatherIconUtil.getIconResource(
                            forecast.weatherCode.toInt(),
                            isDay = !isDarkTheme
                        )
                    ),
                    contentDescription = "Day Icon",
                    modifier = Modifier
                        .width(45.dp)
                        .height(45.dp).weight(1f),
                    contentScale = ContentScale.Fit
                )

                Row(
                    Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp, horizontal = 6.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Icon(
                        painter = painterResource(R.drawable.arrow_up_04),
                        contentDescription = "Arrow up",
                        modifier = Modifier
                            .size(12.dp)
                    )

                    Spacer(Modifier.width(4.dp))

                    Text(
                        text = forecast.maxTemp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )

                    Spacer(Modifier.width(4.dp))
                    VerticalDivider(
                        modifier = Modifier
                            .height(14.dp)
                            .width(1.dp),
                        color = Color.Black.copy(alpha = 0.24f)
                    )
                    Spacer(Modifier.width(2.dp))


                    Icon(
                        painter = painterResource(R.drawable.arrow_down_04),
                        contentDescription = "Arrow down",
                        modifier = Modifier
                            .size(12.dp)
                    )
                    Spacer(Modifier.width(4.dp))

                    Text(
                        text = forecast.minTemp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}