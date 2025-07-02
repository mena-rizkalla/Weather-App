package com.example.weather.core.presentation

import android.content.Context
import com.example.weather.R
import com.example.weather.core.domain.NetworkError

fun NetworkError.toString(context: Context): String {

    val resId = when(this){
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.UNKNOWN_ERROR -> R.string.unknown_error
        NetworkError.SERVER_ERROR -> R.string.server_error
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.SERIALIZATION_ERROR -> R.string.error_serialization

    }
    return context.getString(resId)

}