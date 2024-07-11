package com.uefa.brackets.paresantation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
internal fun MatchesRound(
    round: Round,
    modifier: Modifier = Modifier,
    cardTopBottomPadding: PaddingValues,
    isShowBrackets: Boolean
) {

    Column(
        modifier = modifier
    ) {
        round.matches.forEachIndexed { index, match ->
            MatchItemWithConnection(
                match = match,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(cardTopBottomPadding),
                hasVerticaleLine = index % 2 == 0 && (index + 1) < round.matches.size,
                isShowBrackets = isShowBrackets
            )
        }
    }

}

@Composable
internal fun MatchItemWithConnection(
    match: Match,
    modifier: Modifier = Modifier,
    hasVerticaleLine: Boolean = true,
    isShowBrackets: Boolean
) {

    var size by remember { mutableStateOf(IntSize.Zero.toSize()) }

    //initial height set at 0.dp
    var componentHeight by remember { mutableStateOf(0.dp) }

    // get local density from composable
    val density = LocalDensity.current

    Box(modifier = modifier
        .onGloballyPositioned {
            componentHeight = with(density) {
                it.size.height.toDp()
            }
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .widthIn(min = 270.dp)
                .background(Color.LightGray)
                .onSizeChanged { size = it.toSize() }
                .padding(16.dp)
        ) {
            Column {
                Text(text = match.team1, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = match.team2, style = MaterialTheme.typography.h6)
                match.winner?.let {
                    Text(text = "Winner: $it", style = MaterialTheme.typography.body2)
                }
            }
        }

        Box(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth()
                .align(Alignment.CenterStart)
        ) {
            BracketLine(
                startX = 0f,
                startY = 2f,
                endX = size.width + if (isShowBrackets) 75 else 0,
                endY = 2f
            )
        }


        AnimatedVisibility(visible = isShowBrackets, enter = fadeIn(), exit = fadeOut()) {
            if (isShowBrackets) {
                if (hasVerticaleLine) {
                    Column(
                        modifier = Modifier
                            .height(componentHeight)
                    ) {

                        val startY = (componentHeight.toPx() / 2) - 5
                        val endY = (startY * 3) + 12.dp.toPx()

                        VerticalConnectionLine(
                            startX = size.width + 75,
                            startY = startY,
                            endY = endY
                        )

                        BracketLine(
                            startX = size.width + 75,
                            startY = 28f,
                            endX = (size.width + 75) + 64.dp.toPx(),
                            endY = 28f
                        )

                    }
                }
            }
        }


    }
}

@Composable
internal fun Dp.toPx(): Float {
    val density = LocalDensity.current.density
    return (this * density).value
}
