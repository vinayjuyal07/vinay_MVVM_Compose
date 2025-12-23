package com.example.jetpackcomposeui.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeui.model.ChatMessage
import com.example.jetpackcomposeui.network.use_case.AuthUseCase
import com.example.jetpackcomposeui.utility.FirebaseUtil.observeMessages
import com.example.jetpackcomposeui.utility.FirebaseUtil.sendMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val loginUseCase: AuthUseCase): ViewModel() {


    var messages by mutableStateOf<List<ChatMessage>>(emptyList())
        private set

    fun startChat(receiverId: String) {
        observeMessages(receiverId) {
            messages = it
        }
    }

    fun send(receiverId: String, text: String) {
        sendMessage(receiverId, text)
    }

    fun onClearMessageList(){
        messages = emptyList()
    }
}



