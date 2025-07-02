package com.example.weather.core.domain

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    UNKNOWN_ERROR,
    SERIALIZATION_ERROR,
    SERVER_ERROR
}