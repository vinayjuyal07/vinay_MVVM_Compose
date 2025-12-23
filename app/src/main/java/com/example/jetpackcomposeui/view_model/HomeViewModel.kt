package com.example.jetpackcomposeui.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeui.navigation.FullScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage = _snackbarMessage.asStateFlow()
    fun showSnackbar(message: String) {
        _snackbarMessage.value = message
    }
    fun clearSnackbar() {
        _snackbarMessage.value = null
    }

    // use for when full screen view
    var fullScreen by mutableStateOf<FullScreen>(FullScreen.None)
        private set
    fun open(screen: FullScreen) {
        fullScreen = screen
    }
    fun close() {
        fullScreen = FullScreen.None
    }

}