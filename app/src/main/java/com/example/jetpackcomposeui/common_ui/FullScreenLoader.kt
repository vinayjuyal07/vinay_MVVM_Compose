package com.example.jetpackcomposeui.common_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeui.R

@Composable
fun FullScreenLoader(show: Boolean) {
    if (show) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
                .pointerInput(Unit) {},
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp),
                color = colorResource(id = R.color.red),
                strokeWidth = 5.dp
            )
        }
    }
}
