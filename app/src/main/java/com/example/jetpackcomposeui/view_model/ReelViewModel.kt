package com.example.jetpackcomposeui.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeui.model.ReelResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ReelViewModel @Inject constructor(@ApplicationContext private val context: Context): ViewModel() {
    val reelList = listOf(
        ReelResponse(
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            userName = "user11",
            caption = "This is a sample caption for the reel video. #JetpackCompose"
        ),
        ReelResponse(
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            userName = "user22",
            caption = "This is reel number 22"
        ),
        ReelResponse(
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
            userName = "user33",
            caption = "This is a sample caption for the reel video. #JetpackCompose"
        ),
        ReelResponse(
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
            userName = "user44",
            caption = "This is reel number 44"
        ),
        ReelResponse(
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
            userName = "user55",
            caption = "This is a sample caption for the reel video. #JetpackCompose"
        ),
        ReelResponse(
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
            userName = "user66",
            caption = "This is reel number 66"
        ),
        ReelResponse(
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
            userName = "user77",
            caption = "This is reel number 77"
        ),
        ReelResponse(
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
            userName = "user88",
            caption = "This is a sample caption for the reel video. #JetpackCompose"
        ),
        ReelResponse(
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
            userName = "user99",
            caption = "This is reel number 99"
        ),
        ReelResponse(
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
            userName = "user111",
            caption = "This is a sample caption for the reel video. #JetpackCompose"
        ),
        ReelResponse(
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
            userName = "user222",
            caption = "This is reel number 222"
        ),
        ReelResponse(
            videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
            userName = "user333",
            caption = "This is a sample caption for the reel video. #JetpackCompose"
        )
    )
    val reels = MutableStateFlow(reelList)




}