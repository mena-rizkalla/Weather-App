package com.example.weather.core.data.networking

import com.example.weather.core.domain.NetworkError
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.coroutineContext
import com.example.weather.core.domain.Result

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
) : Result<T, NetworkError> {
    val response = try {
        execute()
    }catch (e: UnresolvedAddressException){
        return Result.Error(NetworkError.NO_INTERNET)
    }catch (e: SerializationException){
        return Result.Error(NetworkError.SERIALIZATION_ERROR)
    }catch (e: Error){
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN_ERROR)
    }

    return responseToResult(response)
}