package com.uefa.wordle.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.uefa.wordle.theming.ui.core.Colors
import com.uefa.wordle.theming.ui.core.Theme
import com.uefa.wordle.theming.ui.core.Typography

object Theme {

    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalTheme.current.colors

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTheme.current.typography

//    val drawables: Drawables
//        @Composable
//        @ReadOnlyComposable
//        get() = LocalResource.current

}

private val LocalTheme = staticCompositionLocalOf { Theme() }


@Composable
fun GameProvider(
    theme: Theme,
    component: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalTheme provides theme,
    ) {
        component()
    }
}