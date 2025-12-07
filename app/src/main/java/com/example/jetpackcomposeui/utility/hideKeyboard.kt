package com.example.jetpackcomposeui.utility

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController


@Composable
fun hideKeyboard(): () -> Unit {
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current

    return {
        focusManager.clearFocus()
        keyboard?.hide()
    }
}
