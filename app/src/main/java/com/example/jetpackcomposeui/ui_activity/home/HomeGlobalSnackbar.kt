package com.example.jetpackcomposeui.ui_activity.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetpackcomposeui.common_ui.TopSnackbar
import com.example.jetpackcomposeui.view_model.HomeViewModel

@Composable
fun HomeGlobalSnackbar(homeViewModel: HomeViewModel) {
    val message by homeViewModel.snackbarMessage.collectAsState()
    if (!message.isNullOrEmpty()) {
        TopSnackbar(
            message = message!!,
            isVisible = true,
            onDismiss = { homeViewModel.clearSnackbar() }
        )
    }
}