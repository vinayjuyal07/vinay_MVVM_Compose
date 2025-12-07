package com.example.jetpackcomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeui.model.MessageType
import com.example.jetpackcomposeui.navigation.NavHostApp
import com.example.jetpackcomposeui.ui_activity.login.LoginScreen
import com.example.jetpackcomposeui.ui_activity.login.LoginUi
import com.example.jetpackcomposeui.ui.theme.JetpackComposeUITheme
import com.example.jetpackcomposeui.ui_activity.home.HomeUi
import com.example.jetpackcomposeui.ui_activity.inbox.MessageCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeUITheme {
                NavHostApp()
                 //LoginScreen()
               // LoginScreenPreview()
                // MessageCard()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginUi(
        email = "",
        password = "",
        showPassword = false,
        onEmailChange = {},
        onPasswordChange = {},
        onTogglePassword = {},
        onLoginClick = {},
        onForgotPasswordClick = {},
        onGoogleSignInClick = {}
    )
}
