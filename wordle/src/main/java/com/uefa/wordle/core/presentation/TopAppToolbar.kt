@file:OptIn(ExperimentalMaterial3Api::class)

package com.uefa.wordle.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.uefa.wordle.theming.Theme

@Composable
internal fun TopAppToolbar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    backgroundImage: Painter? = null,
    navigationIcon: @Composable () -> Unit,
    actionIcon: @Composable () -> Unit = {},
) {

    val toolbarColor = Color(0xFF000FAA)

    Box {

//        if (toolbarImage != null) {
//            Image(
//                modifier = Modifier.matchParentSize(),
//                painter = painterResource(id = toolbarImage), contentDescription = "",
//                contentScale = ContentScale.Crop
//            )
//        } else if (backgroundImage != null) {
//            Image(
//                modifier = Modifier.matchParentSize(),
//                painter = backgroundImage,
//                contentDescription = "",
//                contentScale = ContentScale.Crop
//            )
//        }

        CenterAlignedTopAppBar(
            modifier = modifier.fillMaxWidth(),
            title = {
                title.invoke()
            },
            navigationIcon = {
                navigationIcon.invoke()
            },

            actions = {
                actionIcon.invoke()
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(toolbarColor),
        )

    }

}