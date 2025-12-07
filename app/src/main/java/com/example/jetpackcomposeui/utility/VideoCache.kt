package com.example.jetpackcomposeui.utility

import android.content.Context
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import java.io.File

object VideoCache {
    private var simpleCache: SimpleCache? = null
    fun get(context: Context): SimpleCache {
        if (simpleCache == null) {
            val cacheDir = File(context.cacheDir, "video_cache")

            simpleCache = SimpleCache(
                cacheDir,
                LeastRecentlyUsedCacheEvictor(500 * 1024 * 1024),   // 500MB cache
            )
        }
        return simpleCache!!
    }
}

fun buildCacheDataSourceFactory(context: Context): CacheDataSource.Factory {
    val cache = VideoCache.get(context)
    return CacheDataSource.Factory()
        .setCache(cache)
        .setUpstreamDataSourceFactory(
            DefaultHttpDataSource.Factory()
                .setConnectTimeoutMs(10_000)
                .setReadTimeoutMs(10_000)
        )
        .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
}


