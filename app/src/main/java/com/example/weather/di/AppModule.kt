package com.example.weather.di


import com.example.weather.core.data.networking.HttpClientFactory
import com.example.weather.weather.data.location.AndroidReverseGeocoder
import com.example.weather.weather.data.location.LocationTrackerImpl
import com.example.weather.weather.data.networking.WeatherApiServiceImpl
import com.example.weather.weather.data.networking.WeatherRepositoryImpl
import com.example.weather.weather.domain.LocationTracker
import com.example.weather.weather.domain.WeatherApiService
import com.example.weather.weather.domain.WeatherRepository
import com.example.weather.weather.domain.ReverseGeocoder
import com.example.weather.weather.presentation.weatherScreen.WeatherViewModel
import com.google.android.gms.location.LocationServices
import io.ktor.client.engine.cio.CIO
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Provides a singleton HttpClient instance
    single{HttpClientFactory.create(CIO.create())}

    // Provide FusedLocationProviderClient
    single { LocationServices.getFusedLocationProviderClient(androidApplication()) }

    // Bind LocationTrackerImpl to the LocationTracker interface
    single<LocationTracker> {
        LocationTrackerImpl(
            locationClient = get(),
            application = androidApplication()
        )
    }

    // Add the binding for your new ReverseGeocoder
    single<ReverseGeocoder> {
        AndroidReverseGeocoder(application = get())
    }

    // Provides a singleton WeatherApiService instance
    single<WeatherApiService> { WeatherApiServiceImpl(get(), get()) }

    // Provides a singleton WeatherRepository instance
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    // Provides a ViewModel instance
    viewModel { WeatherViewModel(get(), get()) }
}