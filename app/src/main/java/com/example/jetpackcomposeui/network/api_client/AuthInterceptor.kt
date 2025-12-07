package com.example.jetpackcomposeui.network.api_client

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = tokenManager.getAccessToken()

        val request = chain.request().newBuilder()
            .apply {
                if (!accessToken.isNullOrEmpty()) {
                    header("Authorization", "Bearer $accessToken")
                }
            }
            .build()

        return chain.proceed(request)
    }
}

