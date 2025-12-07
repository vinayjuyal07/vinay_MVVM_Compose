package com.example.jetpackcomposeui.ui_activity.reel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import com.example.jetpackcomposeui.utility.PlayerPool
import com.example.jetpackcomposeui.view_model.ReelViewModel


@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelScreen(viewModel: ReelViewModel  = hiltViewModel()) {

    val reelList by viewModel.reels.collectAsState()
    val context = LocalContext.current
    val pool = remember { PlayerPool(context) }
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { reelList.size })
    // Update current player on page change
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { index ->
                pool.current.setMediaItem(MediaItem.fromUri(reelList[index].videoUrl))
                pool.current.prepare()
                pool.current.playWhenReady = true
                // ----- PREVIOUS -----
                if (index - 1 >= 0) {
                    pool.previous.setMediaItem(MediaItem.fromUri(reelList[index - 1].videoUrl))
                    pool.previous.prepare()
                }
                // ----- NEXT -----
                if (index + 1 < reelList.size) {
                    pool.next.setMediaItem(MediaItem.fromUri(reelList[index + 1].videoUrl))
                    pool.next.prepare()
                }
            }
    }
    DisposableEffect(Unit) { onDispose { pool.releaseAll() } }
    VerticalPager(
        state = pagerState,
        key = { page -> reelList[page].id },
        modifier = Modifier.fillMaxSize(),
    ) { page ->
        val isActive = page == pagerState.currentPage
        val player = when (page) {
            pagerState.currentPage -> pool.current
            pagerState.currentPage - 1 -> pool.previous
            pagerState.currentPage + 1 -> pool.next
            else -> null         // pages far away don't need player
        }

        ReelVideoPlayer(player,reelList[page], isActive)
    }
}