package com.example.jetpackcomposeui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class LoginRequest(var email: String, var password: String)

@Parcelize
data class LoginResponse(val accessToken: String, val refreshToken: String, val expiresIn: Long): Parcelable

