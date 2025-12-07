package com.example.jetpackcomposeui.model

import java.util.UUID


data class ReelResponse(
    var id: String = UUID.randomUUID().toString(),
    var videoUrl: String,
    var userName: String,
    var caption: String
)