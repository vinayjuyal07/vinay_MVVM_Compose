package com.example.jetpackcomposeui.ui_activity.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcomposeui.ActionButtonConstant
import com.example.jetpackcomposeui.ErrorConstant
import com.example.jetpackcomposeui.common_ui.FullScreenLoader
import com.example.jetpackcomposeui.common_ui.TopSnackbar
import com.example.jetpackcomposeui.model.LoginResponse
import com.example.jetpackcomposeui.utility.UiStateHandler
import com.example.jetpackcomposeui.utility.hideKeyboard
import com.example.jetpackcomposeui.view_model.LoginViewModel


@Composable
fun LoginScreen(onLoginSuccess: (LoginResponse) -> Unit,
                onForgotPassword: () -> Unit,  loginViewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    // 👉 Observe LiveData     // ViewModel State
    val uiState by loginViewModel.uiState.observeAsState()
    var emailId by remember { mutableStateOf("Vinay") }
    var password by remember { mutableStateOf("123456") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showLoader by remember { mutableStateOf(false) }

    val mHideKeyboard = hideKeyboard()

    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    //---------------------------------------------------------
    // Handle ViewModel UI State (Clean & Reusable)
    //---------------------------------------------------------
    UiStateHandler(
        uiState = uiState,
        onLoading = { showLoader = true },
        onSuccess = {
            onLoginSuccess(it)
            showLoader = false
            emailId = ""
            password = ""
        },
        onError = { msg ->
            showLoader = false
            snackbarMessage = msg
            showSnackbar = true
        }
    )

    // ---------------------------------------------------------
    // UI Start
    // --------------------------------------------------------
    LoginUi(
        email = emailId,
        password = password,
        showPassword = passwordVisible,
        onEmailChange = { if (it.length <= 50) emailId = it },
        onPasswordChange = { if (it.length <= 15) password = it },
        onTogglePassword = { passwordVisible = !passwordVisible },
        onLoginClick = {
            mHideKeyboard()
            if (emailId.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.loginApi(emailId, password)
            } else {
                snackbarMessage = ErrorConstant.PLEASE_ENTER_EMAIL_PASSWORD
                showSnackbar = true
            }
        },
        onForgotPasswordClick = {
            onForgotPassword()
        },
        onGoogleSignInClick = {
            snackbarMessage = ActionButtonConstant.SIGN_IN_WITH_GOOGLE
            showSnackbar = true
        }
    )

    // ---------------------------------------------------------
    // 🔥 Global reusable loader
    // ---------------------------------------------------------
    FullScreenLoader(showLoader)
    
    // ---------------------------------------------------------
    // 🔥 Global reusable top snackbar
    // ---------------------------------------------------------
    TopSnackbar(message = snackbarMessage,
        isVisible = showSnackbar,
        onDismiss = { showSnackbar = false }
    )
}