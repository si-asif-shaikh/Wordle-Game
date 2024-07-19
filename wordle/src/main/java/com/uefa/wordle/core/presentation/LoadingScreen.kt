package com.uefa.wordle.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
internal fun LoadingScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        MaterialTheme {
            // Show a progress indicator while loading
            CircularProgressIndicator(
                modifier = Modifier.size(28.dp),
                color = Theme.colors.base.interaction,
                strokeCap = StrokeCap.Butt,
                strokeWidth = 4.dp
            )
        }
    }
}