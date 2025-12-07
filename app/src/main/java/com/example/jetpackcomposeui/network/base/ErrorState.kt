package com.example.jetpackcomposeui.network.base

import com.google.gson.annotations.SerializedName

sealed class ErrorState {

    data class ErrorResponse(@SerializedName("status")val status:Any?,
                             @SerializedName("code")val code:Any?,
                             @SerializedName("message")val message:String?)
}