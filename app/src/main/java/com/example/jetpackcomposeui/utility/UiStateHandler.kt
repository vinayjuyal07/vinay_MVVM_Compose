package com.example.jetpackcomposeui.utility

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.jetpackcomposeui.network.base.ResultState

@Composable
fun <T> UiStateHandler(
    uiState: ResultState<T>?,
    onLoading: () -> Unit,
    onSuccess: (T) -> Unit,
    onError: (String) -> Unit
) {
    LaunchedEffect(uiState) {
        when (uiState) {
            is ResultState.Loading -> onLoading()
            is ResultState.Success -> onSuccess(uiState.data)
            is ResultState.Error -> onError(uiState.error.errorMessage ?: "Something went wrong")
            null -> Unit
        }
    }
}
