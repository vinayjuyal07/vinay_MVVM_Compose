package com.example.jetpackcomposeui.network.api_client

import com.example.jetpackcomposeui.network.api.AuthApi
import com.example.jetpackcomposeui.network.model.RefreshTokenRequest
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject



/*class TokenRefreshInterceptor @Inject constructor(
    private val tokenManager: TokenManager,
    private val authApi: AuthApi
) : Interceptor {*/

class TokenRefreshInterceptor @Inject constructor(
    private val tokenManager: TokenManager,
    private val authApi: dagger.Lazy<AuthApi>
) : Interceptor {

    @Synchronized
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var response = chain.proceed(request)

        // If token expired
        if (response.code == 401) {
            response.close()
            val refreshToken = tokenManager.getRefreshToken()

            if (!refreshToken.isNullOrEmpty()) {
                return try {
                    // Call refresh API
                    val newTokens = authApi.get().refreshTokenCall(RefreshTokenRequest(refreshToken))
                    // Save new tokens
                    tokenManager.saveTokens(
                        newTokens.accessToken,
                        newTokens.refreshToken
                    )
                    // Retry original API with new token
                    val newRequest = request.newBuilder()
                        .header("Authorization", "Bearer ${newTokens.accessToken}")
                        .build()
                    chain.proceed(newRequest)
                } catch (e: Exception) {
                    response // fallback
                }
            }
        }
        return response
    }
}


