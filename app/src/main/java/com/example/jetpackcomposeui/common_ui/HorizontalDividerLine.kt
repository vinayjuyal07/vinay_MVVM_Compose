package com.example.jetpackcomposeui.common_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeui.R

@Composable
fun HorizontalDividerLine() {
    Box(Modifier.fillMaxWidth()) {
        HorizontalDivider(
            thickness = 0.5.dp,
            color = colorResource(id = R.color.gray)
        )
    }
}