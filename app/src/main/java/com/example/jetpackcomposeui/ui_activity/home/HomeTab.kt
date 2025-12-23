package com.example.jetpackcomposeui.ui_activity.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackcomposeui.navigation.Routs

sealed class HomeTab(val route: String, val icon: ImageVector, val title: String) {
    object Reel : HomeTab(Routs.REEL, Icons.Default.PlayArrow, Routs.REEL)
    object Chat : HomeTab(Routs.CHAT, Icons.Default.Email, Routs.CHAT)
    object Profile : HomeTab(Routs.PROFILE, Icons.Default.Person, Routs.PROFILE)
    object Inbox : HomeTab(Routs.INBOX, Icons.Default.Home, Routs.INBOX)
    object Settings : HomeTab(Routs.SETTINGS, Icons.Default.Settings, Routs.SETTINGS)
}

val homeTabs = listOf(
    HomeTab.Reel,
    HomeTab.Chat,
    HomeTab.Profile,
    HomeTab.Inbox,
    HomeTab.Settings
)