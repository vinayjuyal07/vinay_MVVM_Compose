package com.example.jetpackcomposeui.ui_activity.home


import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcomposeui.common_ui.BottomNavigationBar
import com.example.jetpackcomposeui.model.LoginResponse
import com.example.jetpackcomposeui.navigation.FullScreen
import com.example.jetpackcomposeui.navigation.FullScreenHost
import com.example.jetpackcomposeui.navigation.Routs
import com.example.jetpackcomposeui.ui_activity.chat.UserListScreen
import com.example.jetpackcomposeui.ui_activity.inbox.InboxScreen
import com.example.jetpackcomposeui.ui_activity.profile.ProfileScreen
import com.example.jetpackcomposeui.ui_activity.reel.ReelScreen
import com.example.jetpackcomposeui.ui_activity.settings.SettingsScreen
import com.example.jetpackcomposeui.view_model.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(loginResponse: LoginResponse?) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    var currentTab by remember { mutableStateOf<HomeTab>(HomeTab.Inbox) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val isReelScreen = currentTab == HomeTab.Reel
    val tabHistory = remember { mutableStateListOf<HomeTab>(currentTab) }
    // one by one to back stack
    /*BackHandler {
        if (tabHistory.size > 1) {
            tabHistory.removeLast()
            currentTab = tabHistory.last()
        } else {
            // Only one tab left, let system handle back (exit app)
        }
    }*/

    val inboxTab = homeTabs.first { it.route == Routs.INBOX }
    // // direct using inbox back handling. // direct to inbox back stack
    BackHandler(enabled = currentTab != inboxTab) {
        // Only handle back if NOT in inbox
        currentTab = inboxTab
        tabHistory.clear()
        tabHistory.add(inboxTab)
    }
    val isFullScreen = homeViewModel.fullScreen != FullScreen.None
    Scaffold(
        modifier = if (isReelScreen) Modifier else Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (!isFullScreen){ if (!isReelScreen) HomeTopBar(title = currentTab.route, scrollBehavior)
                 else Box(Modifier
                .fillMaxWidth()
                .height(WindowInsets.statusBars.asPaddingValues()
                    .calculateTopPadding()).background(Color.Red))
                 }else Box(Modifier
                .fillMaxWidth()
                .height(WindowInsets.statusBars.asPaddingValues()
                    .calculateTopPadding()).background(Color.Red))},

        bottomBar = {
            if (!isFullScreen) {
            BottomNavigationBar(
                selectedTab = currentTab,
                onTabSelected = { tab ->
                    if (tab != currentTab) {
                        currentTab = tab
                        tabHistory.add(tab)
                    }
                }
            )}

        }
    ) { padding ->
        // ---------- Smooth animated content ----------
        AnimatedContent(
            targetState = currentTab,
            transitionSpec = {
                val initialIndex = homeTabs.indexOf(initialState)
                val targetIndex = homeTabs.indexOf(targetState)
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> if (targetIndex > initialIndex) fullWidth else -fullWidth },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(300)) with
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> if (targetIndex > initialIndex) -fullWidth else fullWidth },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) { tab ->
            if (isFullScreen) {
                FullScreenHost(homeViewModel)
            }else {
                when (tab) {
                    HomeTab.Inbox -> InboxScreen(homeViewModel)
                    HomeTab.Chat -> UserListScreen(homeViewModel)
                    HomeTab.Reel -> ReelScreen()
                    HomeTab.Profile -> ProfileScreen()
                    HomeTab.Settings -> SettingsScreen()
                }
            }
        }
    }

    // Global snackbar view
    HomeGlobalSnackbar(homeViewModel)

   /*  using normal navigation
   val homeViewModel: HomeViewModel = hiltViewModel()
    val navController = rememberNavController()
    // to detect which tab is active
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route ?: HomeTab.Inbox.route

    // Change title based on selected tab
    val title = when (currentRoute) {
        HomeTab.Reel.route -> Routs.REEL
        HomeTab.Profile.route -> Routs.PROFILE
        HomeTab.Inbox.route -> Routs.INBOX
        HomeTab.Settings.route -> Routs.SETTINGS
        else ->  Routs.INBOX
    }
    val isReelScreen = currentRoute == HomeTab.Reel.route
    val scrollBehavior =  TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = if (isReelScreen) Modifier else Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (!isReelScreen) HomeTopBar(title, scrollBehavior)
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->
        // ⭐ Correct place for navigation content!
        NavHost(
            navController = navController,
            startDestination = HomeTab.Inbox.route,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()     // IMPORTANT
        ) {
            composable(HomeTab.Reel.route) { ReelScreen() }
            composable(HomeTab.Profile.route) { ProfileScreen() }
            composable(HomeTab.Inbox.route) { InboxScreen(homeViewModel) }
            composable(HomeTab.Settings.route) { SettingsScreen() }
        }
    }
*/
}


