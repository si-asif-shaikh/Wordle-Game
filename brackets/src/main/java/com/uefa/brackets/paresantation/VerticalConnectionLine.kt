package com.uefa.brackets.paresantation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VerticalConnectionLine(startX:Float,startY:Float = 0f,endY:Float) {
    Canvas(modifier = Modifier
        .fillMaxHeight()
        .width(4.dp)) {
        drawLine(
            color = Color.Black,
            start = Offset(startX, startY),
            end = Offset(startX + 2f, endY),
            strokeWidth = 4f
        )
    }
}
