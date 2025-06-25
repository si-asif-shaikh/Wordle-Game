package com.uefa.wordle

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.uefa.wordle.core.presentation.GameProvider
import com.uefa.wordle.core.presentation.navigation.AppNavigation
import com.uefa.wordle.theming.Theme

@Composable
fun CoreGame(bundle: Bundle ?= null, gameConfig: GameConfig = GameConfig(theme = Theme)) {

    val navController = rememberNavController()

    GameProvider(
        theme = gameConfig.theme
    ) {
        AppNavigation(navController)
    }

}