package com.example.jetpackcomposeui.network.api_client

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(
    private val prefs: SharedPreferences
) {
    private val KEY_ACCESS = "key_access_token"
    private val KEY_REFRESH = "key_refresh_token"

    fun saveTokens(accessToken: String, refreshToken: String) {
        prefs.edit().putString(KEY_ACCESS, accessToken)
            .putString(KEY_REFRESH, refreshToken)
            .apply()
    }

    fun getAccessToken(): String? = prefs.getString(KEY_ACCESS, null)
    fun getRefreshToken(): String? = prefs.getString(KEY_REFRESH, null)

    fun clearTokens() {
        prefs.edit().remove(KEY_ACCESS).remove(KEY_REFRESH).apply()
    }
}
