package com.example.jetpackcomposeui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeui.AppConstant
import com.example.jetpackcomposeui.model.LoginResponse
import com.example.jetpackcomposeui.ui_activity.home.HomeScreen
import com.example.jetpackcomposeui.ui_activity.login.LoginScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {
    val navController = rememberNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Routs.Login,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(1000,easing = FastOutSlowInEasing)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(1000,easing = FastOutSlowInEasing)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(1000,easing = FastOutSlowInEasing)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(1000,easing = FastOutSlowInEasing)
            )
        }
    ) {
        composable(Routs.Login) {
            LoginScreen(
                onLoginSuccess = { loginResponse ->
                    navController.navigate(Routs.Home) {
                        popUpTo(Routs.Login) { inclusive = true }
                    }
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set(AppConstant.LOGIN_RESPONSE, loginResponse)
                },
                onForgotPassword = { }
            )
        }
        composable(Routs.Home) { entry ->
            val loginResponse = entry.savedStateHandle
                .get<LoginResponse>(AppConstant.LOGIN_RESPONSE)
            HomeScreen(loginResponse)
        }
    }
}