package com.example.weather.core.presentation

import com.example.weather.R

object WeatherIconUtil {
    fun getIconResource(weatherCode: Int, isDay: Boolean = true): Int {
        // This is a simplified mapping. You'll want to create a complete one.
        return when (weatherCode) {
            0 -> if (isDay) R.drawable.clearsky1 else R.drawable.clearsky1
            1 -> if (isDay) R.drawable.mainlyclear1 else R.drawable.mainlyclear
            2 -> if (isDay) R.drawable.partlycloudy1 else R.drawable.partlycloudy
            3 -> R.drawable.overcast // Same for day and night

            45 -> if (isDay) R.drawable.fog1 else R.drawable.fog1
            48 -> if (isDay) R.drawable.depositingrimefog1 else R.drawable.depositingrimefog // Same for day and night

            51 -> if (isDay) R.drawable.drizzlelight1 else R.drawable.drizzlelight
            53 -> if (isDay) R.drawable.drizzlemoderate1 else R.drawable.diizzlemoderate
            55 -> if (isDay) R.drawable.drizzleintensity1 else R.drawable.drizzleintensity

            56 -> if (isDay) R.drawable.freezingdrizzlelight1 else R.drawable.freezingdrizzlelight1
            57 -> if (isDay) R.drawable.freezingdrizzleintensity1 else R.drawable.freezingdrizzleintensity

            61 -> if (isDay) R.drawable.rainintensity1 else R.drawable.rainintensity
            63 -> if (isDay) R.drawable.rainmoderate1 else R.drawable.rainmoderate
            65 -> if (isDay) R.drawable.rainintensity1 else R.drawable.rainintensity

            66 -> if (isDay) R.drawable.freezingheavy1 else R.drawable.freezingheavy
            67 -> if (isDay) R.drawable.freezinglight else R.drawable.freezingloght

            71 -> if (isDay) R.drawable.snowfalllight1 else R.drawable.snowfalllight
            73 -> if (isDay) R.drawable.snowfallmoderate1 else R.drawable.snowfallmoderate1
            75 -> if (isDay) R.drawable.snowfallintensity1 else R.drawable.snowfallintensity
            77 -> if (isDay) R.drawable.snowgrains1 else R.drawable.snowgrains

            80 -> if (isDay) R.drawable.rainshowerslight1 else R.drawable.rainshowerslight
            81 -> if (isDay) R.drawable.rainshowermoderate1 else R.drawable.rainshowermoderate
            82 -> if (isDay) R.drawable.rainshowerviolent1 else R.drawable.tainshowerviolent

            85 -> if (isDay) R.drawable.snowshowerslight1 else R.drawable.snowshowerslight
            86 -> if (isDay) R.drawable.snowshowerheavy1 else R.drawable.snowshowerheavy

            95 -> if (isDay) R.drawable.thunderstromslightormoderate1 else R.drawable.thunderstromslightormoderate
            96, 99 -> if (isDay) R.drawable.thunderstromwithslighthail1 else R.drawable.thunderstromwithslighthail

            // A sensible default icon for any unknown codes
            else -> if (isDay) R.drawable.dayicon else R.drawable.dayicon
        }
    }
}