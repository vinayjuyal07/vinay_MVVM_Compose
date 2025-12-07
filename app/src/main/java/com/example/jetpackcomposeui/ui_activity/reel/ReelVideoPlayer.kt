package com.example.jetpackcomposeui.ui_activity.reel

import androidx.annotation.OptIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.jetpackcomposeui.model.ReelResponse


@OptIn(UnstableApi::class)
@Composable
fun ReelVideoPlayer(player: ExoPlayer?, reelItem: ReelResponse, isActive: Boolean) {

    var isPlaying by remember { mutableStateOf(true) }

   // when menu option click then video paused
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, isActive) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    // 🔥 When user presses Menu/Home button
                    player?.playWhenReady = false
                    isPlaying = false     // Show play icon
                }
                Lifecycle.Event.ON_RESUME -> {
                    // 🔥 Resume only if this reel is active page
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .clickable {
            isPlaying = !isPlaying
            player?.playWhenReady = isPlaying
        },
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { clip = false }, // smooth scrolling
            factory = { ctx ->
                PlayerView(ctx).apply {
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    // 🔥 Prevent flicker
                    setKeepContentOnPlayerReset(true)
                    keepScreenOn = true
                }
            },
            update = {
                if (it.player != player) it.player = player
            }
        )

        if (!isPlaying && isActive) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center)
            )
        }


        // Right side actions
        RightSideActions(
            player=player,
            onLikeClick = {},
            onCommentClick = {},
            onShareClick = {},
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        )

        // Bottom username + caption
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = "${reelItem.userName}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Text(
                text = "This is a sample caption for the reel video. #JetpackCompose",
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 2
            )
        }
    }
}


