package com.uefa.wordle.theming.ui.core

import androidx.compose.ui.text.font.FontFamily


data class Theme(
    val colors: Colors = Colors(),
    val typography: Typography = Typography(),
    val shapes: Shapes = Shapes(),
    val shadows: Shadows = Shadows(),
    val sizes: Sizes = Sizes(),
    val appFont: FontFamily = FontFamily.Default
) {
    fun isDark() = colors.neutral is Colors.Neutral.Dark
}