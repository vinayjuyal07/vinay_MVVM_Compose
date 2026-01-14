package com.example.jetpackcomposeui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import com.example.jetpackcomposeui.ui_activity.chat.ChatScreen
import com.example.jetpackcomposeui.ui_activity.chat.chat_bot.AIChatBotScreen
import com.example.jetpackcomposeui.view_model.HomeViewModel

@Composable
fun FullScreenHost(homeViewModel: HomeViewModel) {

    BackHandler(enabled = homeViewModel.fullScreen != FullScreen.None) {
        homeViewModel.close()
    }
    when (val screen = homeViewModel.fullScreen) {
        is FullScreen.ChatUI -> ChatScreen(
            userData = screen.userData,
            onBack = { homeViewModel.close() }
        )
        is FullScreen.AiChatBotUI-> AIChatBotScreen(
            userId = screen.userId,
            onBack = { homeViewModel.close() }
        )

        else -> Unit
    }
}

