package com.uefa.wordle.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.uefa.wordle.core.presentation.MainViewModel
import com.uefa.wordle.presentation.WordleGame
import com.uefa.wordle.presentation.splash.SplashScreen

@Composable
internal fun AppNavigation(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val composeNavigator = viewModel.appComposeNavigator
    LaunchedEffect(context) {
        composeNavigator.handleNavigationCommands(navController)
    }


    NavHost(navController = navController, startDestination = "onboarding") {

        navigation(
            startDestination = Screens.Splash.name, route = "onboarding"
        ) {

            composable(route = Screens.Splash.name) { _ ->
                SplashScreen(state = viewModel.uiState, init = {
                    viewModel.initGame()
                }, onComplete = {
                    composeNavigator.navigateAndClearBackStack(Screens.WordleGame.createRoute(10))
                })
            }

            val wordleGameScreen = Screens.WordleGame
            composable(
                route = wordleGameScreen.name,
                arguments = wordleGameScreen.navArguments
            ) { _ ->
                WordleGame()
            }

        }

    }

}