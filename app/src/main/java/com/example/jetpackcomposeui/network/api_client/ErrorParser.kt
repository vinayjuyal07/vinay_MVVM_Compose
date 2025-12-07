package com.example.jetpackcomposeui.network.api_client

import android.util.MalformedJsonException
import com.example.jetpackcomposeui.network.base.ErrorEntity
import com.example.jetpackcomposeui.network.base.ErrorState
import com.example.jetpackcomposeui.network.base.ResultState
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.SocketException
import java.net.SocketTimeoutException

/**
 * This method returns the proper error codes and error messages for various
 * network exceptions and API related errors
 */
abstract class ErrorParser {

    fun errorHandle(throwable: Throwable): ResultState.Error<ResultState<ErrorEntity.Error>> {
        return when (throwable) {

            is SocketTimeoutException, is SocketException, is InterruptedIOException -> {
                ResultState.Error(
                    ErrorEntity.Error(
                        NetworkConstants.SERVICE_UNAVAILABLE_CODE,
                        NetworkConstants.SERVICE_UNAVAILABLE
                    )
                )
            }

            is MalformedJsonException -> {
                ResultState.Error(
                    ErrorEntity.Error(
                        NetworkConstants.MALFORMED_JSON_CODE,
                        NetworkConstants.MALFORMED_JSON
                    )
                )
            }
            is IOException -> {
                ResultState.Error(
                    ErrorEntity.Error(
                        NetworkConstants.NO_INTERNET_CODE,
                        NetworkConstants.NO_INTERNET
                    )
                )
            }

            is HttpException -> {
                val errorResponse: ErrorState.ErrorResponse? =
                    getError(throwable.response()?.errorBody())
                if (errorResponse?.code == null) {
                    ResultState.Error(
                        ErrorEntity.Error(
                            NetworkConstants.UNEXPECTED_ERROR_CODE,
                            NetworkConstants.UNEXPECTED_ERROR
                        )
                    )
                } else {
                    ResultState.Error(
                        ErrorEntity.Error(
                            errorResponse.code,
                            errorResponse.message.toString()
                        )
                    )
                }
            }
            else -> {
                ResultState.Error(
                    ErrorEntity.Error(
                        NetworkConstants.UNEXPECTED_ERROR_CODE,
                        NetworkConstants.UNEXPECTED_ERROR
                    )
                )
            }
        }
    }

    private fun getError(responseBody: ResponseBody?): ErrorState.ErrorResponse? {
        return try {
            val response = GsonBuilder().create()
                .fromJson(responseBody?.string(), ErrorState.ErrorResponse::class.java)
            response
        } catch (ex: Exception) {
            ErrorState.ErrorResponse(NetworkConstants.UNEXPECTED_ERROR_CODE,
                NetworkConstants.UNEXPECTED_ERROR_CODE,NetworkConstants.UNEXPECTED_ERROR
            )
        }
    }
}
