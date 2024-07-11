package com.uefa.brackets.paresantation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun BracketLine(
    startX: Float,
    startY: Float,
    endX: Float,
    endY: Float,
    isHorizontal: Boolean = true
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        if (isHorizontal) {
            drawLine(
                color = Color.Black,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = 4f
            )
        } else {
            drawLine(
                color = Color.Black,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = 4f
            )
        }
    }
}