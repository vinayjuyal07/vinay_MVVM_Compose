package com.example.jetpackcomposeui.ui_activity.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackcomposeui.navigation.Routs

sealed class HomeTab(val route: String, val icon: ImageVector, val title: String) {
    object Reel : HomeTab(Routs.REEL, Icons.Default.PlayArrow, Routs.REEL)
    object Profile : HomeTab(Routs.PROFILE, Icons.Default.Person, Routs.PROFILE)
    object Inbox : HomeTab(Routs.INBOX, Icons.Default.Email, Routs.INBOX)
    object Settings : HomeTab(Routs.SETTINGS, Icons.Default.Settings, Routs.SETTINGS)
}

val homeTabs = listOf(
    HomeTab.Reel,
    HomeTab.Profile,
    HomeTab.Inbox,
    HomeTab.Settings
)