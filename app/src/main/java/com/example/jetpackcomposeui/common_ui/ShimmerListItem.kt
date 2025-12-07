package com.example.jetpackcomposeui.common_ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeui.R


@Composable
fun ShimmerListItem() {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .fillMaxWidth()
            .height(80.dp)
            .shimmer()
            .background(color = colorResource(id = R.color.light_gray), RoundedCornerShape(2.dp))
    )

}

fun Modifier.shimmer(
    shimmerWidth: Float = 0.3f, // portion of width used by the shiny band
    durationMillis: Int = 500
): Modifier = composed {
    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    this.drawWithContent {
        // draw original content
        drawContent()

        // compute gradient
        val widthPx = size.width
        val shimmerSize = widthPx * shimmerWidth
        val startX = -shimmerSize + (widthPx + shimmerSize) * progress

        val colors = listOf(
            Color.Transparent,
            Color.White.copy(alpha = 0.24f),
            Color.Transparent
        )

        val brush = Brush.linearGradient(
            colors,
            start = Offset(startX, 0f),
            end = Offset(startX + shimmerSize, 0f)
        )

        // overlay brush
        drawRect(brush = brush, size = size)
    }
}
