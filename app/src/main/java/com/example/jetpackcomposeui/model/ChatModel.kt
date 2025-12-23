package com.example.jetpackcomposeui.model

data class ChatMessage(
    val senderId: String = "",
    val receiverId: String = "",
    val message: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
data class UserModel(
    val uid: String = "",
    val name: String = "",
    val email: String = ""
)
