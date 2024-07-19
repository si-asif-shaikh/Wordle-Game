package com.uefa.wordle.wordlegame.presentation.splash

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uefa.wordle.core.presentation.MainContract
import com.uefa.wordle.core.presentation.Theme
import kotlinx.coroutines.delay

@Composable
internal inline fun SplashScreen(
    state: MainContract.State = MainContract.State(),
    crossinline init: () -> Unit,
    crossinline onComplete: () -> Unit = {},
) {


    LaunchedEffect(Unit) {
        delay(1000)
        init() //initialising game with all the required data
    }

    LaunchedEffect(key1 = state.initializationComplete) {
        if (state.initializationComplete) {
            //screenParams.value = track?.getScreenParams(GameScreens.Splash.screenTrackingKey)
            onComplete()
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Theme.colors.elevation.background),
        contentAlignment = Alignment.Center,
    ) {


        Column(
            modifier = Modifier
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(15.dp))


            Text(
                text = "Wordle Game",
                style = Theme.typography.SplashTitle.copy(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Theme.colors.neutral.text01,
                    fontSize = 32.sp
                ),
            )


        }
    }

}

@Composable
@Preview
private fun SplashScreenPreview() {
    SplashScreen( init = {}, onComplete = {})
}