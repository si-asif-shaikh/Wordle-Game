package com.uefa.wordle.theming.ui.core

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Shadows(
    val shadow01: Shadow = Shadow(Color(0x26000000), Offset(0f, 1f), 2f),
    val shadow02: Shadow = Shadow(Color(0x1F000000), Offset(0f, 2f), 6f),
    val shadow03: Shadow = Shadow(Color(0x1A000000), Offset(0f, 6f), 12f),
    val elevation01: Dp = 2.dp,
    val elevation02: Dp = 4.dp,
    val elevation03: Dp = 8.dp,
)
