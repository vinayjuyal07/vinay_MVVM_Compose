package com.example.jetpackcomposeui.network.base

sealed class ResultState<T> {
    data class Loading<T>(val data: T? = null) : ResultState<T>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error<T>(val error: ErrorEntity.Error) : ResultState<T>()
}
sealed class ErrorEntity {
    data class Error(val errorCode: Any?, val errorMessage: String? = "") : ErrorEntity()
}

