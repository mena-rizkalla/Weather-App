package com.example.weather

import android.app.Application
import com.example.weather.di.appModule
import io.ktor.http.ContentType
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherAPP: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherAPP)
            androidLogger()
            modules(appModule)
        }
    }
}