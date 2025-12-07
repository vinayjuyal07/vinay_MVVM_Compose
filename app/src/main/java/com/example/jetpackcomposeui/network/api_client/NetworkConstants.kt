package com.example.jetpackcomposeui.network.api_client

object NetworkConstants {
    const val SERVICE_UNAVAILABLE_CODE= 500
    const val MALFORMED_JSON_CODE= 501
    const val NO_INTERNET_CODE = 502
    const val UNEXPECTED_ERROR_CODE = 503
    const val HTML_RESPONSE_ERROR_CODE = 504


    const val SERVICE_UNAVAILABLE = "System temporarily unavailable, please try again later"
    const val MALFORMED_JSON = "Oops! We hit an error. Try again later."
    const val NO_INTERNET = "Oh! You are not connected to a wifi or cellular data network. Please connect and try again"
    const val UNEXPECTED_ERROR = "Something went wrong"
}