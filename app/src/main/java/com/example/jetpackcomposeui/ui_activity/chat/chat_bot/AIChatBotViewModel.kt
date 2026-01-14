package com.example.jetpackcomposeui.ui_activity.chat.chat_bot

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AIChatBotViewModel @Inject constructor(): ViewModel() {


    val chatList = mutableStateListOf<ChatItem>().apply {
        add(
            ChatItem.BotMessage(
                "Welcome! Open Masters games Abu Dhabi how may I Assist you today?",
                "10:40 AM"
            )
        )
        add(
            ChatItem.UserMessage(
                "I'm planning to participate in Archery Sports?",
                "10:40 AM"
            )
        )
        add(
            ChatItem.ImageCard(
                title = "Archery",
                imageRes = R.drawable.logo
            )
        )
    }

    fun send(text: String) {
        chatList.add(
            ChatItem.UserMessage(
                text = text,
                time = "10:40 AM"
            )
        )
    }


    fun onClearMessageList() {
        // chatList.clear()
    }
}

sealed class ChatItem {
    data class BotMessage(val text: String, val time: String) : ChatItem()
    data class UserMessage(val text: String, val time: String) : ChatItem()
    data class ImageCard(val title: String, val imageRes: Int) : ChatItem()
}