package com.example.jetpackcomposeui.network.api

import com.example.jetpackcomposeui.model.LoginRequest
import com.example.jetpackcomposeui.model.LoginResponse
import com.example.jetpackcomposeui.network.model.RefreshTokenRequest
import com.example.jetpackcomposeui.network.model.RefreshTokenResponse
import com.example.jetpackcomposeui.network.model.UserDetailsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/refresh")
    fun refreshTokenCall(@Body request: RefreshTokenRequest): RefreshTokenResponse

    @GET("user/profile/{mUserId}")
    suspend fun getUserDetailsApi(@Path("mUserId") mUserId: Int): UserDetailsResponse
}
