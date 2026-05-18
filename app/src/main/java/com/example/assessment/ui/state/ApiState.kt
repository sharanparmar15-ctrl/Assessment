package com.example.assessment.ui.state

sealed class ApiState<out T> {
    data object Idle : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()
    data class Success<T>(val response: T) : ApiState<T>()
    data class Error<out T>(val error : T) : ApiState<T>()
}