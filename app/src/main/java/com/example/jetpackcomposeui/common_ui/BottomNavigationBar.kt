package com.example.jetpackcomposeui.common_ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpackcomposeui.R
import com.example.jetpackcomposeui.ui_activity.home.HomeTab
import com.example.jetpackcomposeui.ui_activity.home.homeTabs



// using normal navigation
@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val currentRoute = navController
        .currentBackStackEntryAsState()
        .value?.destination?.route

    NavigationBar(
        containerColor = colorResource(id = R.color.red),
        contentColor = colorResource(id = R.color.white)
    ) {
        homeTabs.forEach { tab ->
            NavigationBarItem(
                selected = currentRoute == tab.route,
                // 🔥 NORMAL navigation (DO NOT CLEAR STACK)
                onClick = {
                    if (currentRoute != tab.route) {
                        navController.navigate(tab.route) // one by one to back stack
                    }
                },
                /*onClick = {
                        navController.navigate(tab.route) {    // direct to inbox back stack
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(HomeTab.Inbox.route) { saveState = true }
                        }
                },*/
                icon = { Icon(tab.icon, contentDescription = tab.title) },
                label = { Text(tab.title) },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.red),
                    selectedTextColor = colorResource(id = R.color.white),
                    unselectedIconColor = colorResource(id = R.color.white).copy(alpha = 0.6f),
                    unselectedTextColor = colorResource(id = R.color.white).copy(alpha = 0.6f),
                    indicatorColor = colorResource(id = R.color.red_white)
                )
            )
        }
    }
}


@Composable
fun BottomNavigationBar(selectedTab: HomeTab, onTabSelected: (HomeTab) -> Unit) {
    NavigationBar( containerColor = colorResource(id = R.color.red),
        contentColor = colorResource(id = R.color.white)) {
        homeTabs.forEach { tab ->
            NavigationBarItem(
                selected = tab == selectedTab,
                onClick = { onTabSelected(tab) },
                icon = { Icon(tab.icon, contentDescription = tab.route) },
                label = { Text(tab.route) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.red),
                    selectedTextColor = colorResource(id = R.color.white),
                    unselectedIconColor = colorResource(id = R.color.white).copy(alpha = 0.6f),
                    unselectedTextColor = colorResource(id = R.color.white).copy(alpha = 0.6f),
                    indicatorColor = colorResource(id = R.color.red_white)
                )
            )
        }
    }
}
