package com.example.jetpackcomposeui.utility

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory

// --------------------------- PlayerPool (3 players) ---------------------------
@UnstableApi
class PlayerPool(context: Context) {
    private val cacheFactory = buildCacheDataSourceFactory(context)
    val previous = ExoPlayer.Builder(context)
        .setMediaSourceFactory(DefaultMediaSourceFactory(cacheFactory))
        .build()
        .apply {
            repeatMode = Player.REPEAT_MODE_ONE   // 🔁 auto-loop
        }
    val current = ExoPlayer.Builder(context)
        .setMediaSourceFactory(DefaultMediaSourceFactory(cacheFactory))
        .build()
        .apply {
            repeatMode = Player.REPEAT_MODE_ONE   // 🔁 auto-loop
        }

    val next = ExoPlayer.Builder(context)
        .setMediaSourceFactory(DefaultMediaSourceFactory(cacheFactory))
        .build()
        .apply {
            repeatMode = Player.REPEAT_MODE_ONE   // 🔁 auto-loop
        }

    fun releaseAll() {
        previous.release()
        current.release()
        next.release()
    }
}
