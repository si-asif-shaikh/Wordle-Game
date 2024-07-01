package com.uefa.wordle.theming.ui.core

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

@Suppress("PropertyName")
data class Shapes(
    val XS2: RoundedCornerShape = RoundedCornerShape(2.dp),
    val XS: RoundedCornerShape = RoundedCornerShape(4.dp),
    val S: RoundedCornerShape = RoundedCornerShape(8.dp),
    val M: RoundedCornerShape = RoundedCornerShape(10.dp),
    val L: RoundedCornerShape = RoundedCornerShape(14.dp),
    val XL: RoundedCornerShape = RoundedCornerShape(20.dp),
    val XL2: RoundedCornerShape = RoundedCornerShape(28.dp),
    val XL3: RoundedCornerShape = RoundedCornerShape(36.dp),
)
