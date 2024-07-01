package com.uefa.wordle.presentation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun FlipCard(
    modifier: Modifier = Modifier,
    cardColors: Color,
    front: @Composable () -> Unit,
    back: @Composable () -> Unit,
    isFlipped: Boolean
) {
    val transition = updateTransition(targetState = isFlipped, label = "Flip Card")

    val rotation by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 600, easing = LinearOutSlowInEasing)
        },
        label = "Rotation"
    ) { state ->
        if (state) 180f else 0f
    }

    val color by transition.animateColor(
        transitionSpec = {
            tween(durationMillis = 600, easing = LinearOutSlowInEasing)
        },
        label = "Color"
    ) { state ->
        cardColors
    }

    Box(
        modifier = modifier
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .background(color, shape = RoundedCornerShape(14.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    rotationY = if (rotation > 90f) 180f else 0f
                },
            contentAlignment = Alignment.Center
        ) {
            if (rotation > 90f) {
                back()
            } else {
                front()
            }
        }
    }

}