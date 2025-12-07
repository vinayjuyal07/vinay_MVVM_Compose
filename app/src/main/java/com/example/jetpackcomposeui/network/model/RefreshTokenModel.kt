package com.example.jetpackcomposeui.network.model

data class RefreshTokenRequest(val refreshToken: String)
data class RefreshTokenResponse(val accessToken: String, val refreshToken: String, val expiresIn: Long)
