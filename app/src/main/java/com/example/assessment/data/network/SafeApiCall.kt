package com.example.assessment.data.network

import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkHandler @Inject constructor() {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): ApiResult<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful && response.body() != null) {
                ApiResult.Success(response.body()!!)       // ✅ 200/201
            } else {
                ApiResult.Error(getHttpError(response.code())) // ✅ 400, 401, 500 etc
            }
        } catch (e: SocketTimeoutException) {
            ApiResult.Error("Request timed out. Please try again.")
        } catch (e: UnknownHostException) {
            ApiResult.Error("No internet connection.")
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Something went wrong.")
        }
    }
}

private fun getHttpError(code: Int): String {
    return when (code) {
        400  -> "Invalid request. Please check your input."
        401  -> "Unauthorized. Please login again."
        404  -> "Resource not found."
        429  -> "Too many requests. Please try again later."
        in 500..599 -> "Server error. Please try again later."
        else -> "Unexpected error: $code"
    }
}