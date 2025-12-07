package com.example.jetpackcomposeui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class InboxRequest(
    val page: Int = 0,
    val hasMore: Boolean = true,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false
)
@Parcelize
data class InboxResponse(
    val items: List<MessageType> = emptyList(),
    val page: Int = 0,
    val hasMore: Boolean = true,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false
):Parcelable

@Parcelize
data class MessageType(
    val id: Int,
    val title: String,
    val body: String,
    var image: String
):Parcelable