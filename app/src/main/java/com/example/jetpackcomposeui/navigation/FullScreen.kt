package com.example.jetpackcomposeui.navigation

import com.example.jetpackcomposeui.model.UserModel

sealed class FullScreen {
    object None : FullScreen()
    data class ChatUI(val userData: UserModel) : FullScreen()
    data class ProfileUI(val userId: String) : FullScreen()
}