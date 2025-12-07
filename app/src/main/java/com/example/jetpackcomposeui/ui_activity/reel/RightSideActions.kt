package com.example.jetpackcomposeui.ui_activity.reel

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.exoplayer.ExoPlayer
import com.example.jetpackcomposeui.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RightSideActions(
    player: ExoPlayer?,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        ActionButton(Icons.Default.Favorite, "1.2K", onLikeClick)
        ActionButton(Icons.Default.MailOutline, "234", onCommentClick)
        ActionButton(Icons.Default.Share, "Share", onShareClick)
        player?.let {
            MuteButton(
                exoPlayer = it,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun ActionButton(icon: ImageVector, label: String, onClick: () -> Unit) {
    val scope = rememberCoroutineScope()
    var scale by remember { mutableStateOf(1f) }
    val scaleAnim by animateFloatAsState(scale)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = {
                scale = 1.3f
                onClick()
                scope.launch {
                    delay(200)
                    scale = 1f
                }
            },
            modifier = Modifier.scale(scaleAnim)
        ) {
            Icon(
                icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }

        Text(label, color = Color.White, fontSize = 12.sp)
    }
}

@Composable
fun MuteButton(
    exoPlayer: ExoPlayer,
    modifier: Modifier = Modifier
) {
    exoPlayer?.volume= 1F
    var muted by remember { mutableStateOf(false) }
    LaunchedEffect(muted) {
        exoPlayer.volume = if (muted) 0f else 1f
    }
    IconButton(
        onClick = {
            muted = !muted
        },
        modifier = modifier
            .size(48.dp)
            .background(Color(0x55000000), CircleShape)
    ) {
        Icon(painter  = if (muted) painterResource(R.drawable.ic_mute_off_24) else painterResource(R.drawable.ic_un_mute_off_24),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(40.dp)
        )
    }
}
