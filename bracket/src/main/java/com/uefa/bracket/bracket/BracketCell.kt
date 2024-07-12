package com.uefa.bracket.bracket

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.pow

@Composable
fun BracketCell(
    matchData: MatchData,
    heightScalingExponent: Int,
    isTopMatch: Boolean,
    isCollapsed: Boolean,
    isFirstColumn: Boolean,
    isLastColumn: Boolean
) {
    val lineColor = Color.Black
    val height by animateDpAsState(targetValue = 100.dp * (2.0.pow(heightScalingExponent.toDouble())).toFloat())

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        verticalAlignment = Alignment.CenterVertically
    ) {
        
        LeftLineArea(isFirstColumn, lineColor)

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            val duration = 500

            AnimatedVisibility(visible = !isCollapsed, enter = expandVertically(
                animationSpec = tween(durationMillis = duration)), exit = shrinkVertically(tween(durationMillis = duration))) {
                TopLabelArea()
            }

            TeamScoreArea(team = matchData.team1, score = matchData.team1Score)
            TeamScoreArea(team = matchData.team2, score = matchData.team2Score)

            AnimatedVisibility(visible = !isCollapsed, enter = expandVertically(tween(durationMillis = duration)) ,exit = shrinkVertically(tween(durationMillis = duration))) {
                TouchForMoreInfoArea()
            }
        }

        RightLineArea(isLastColumn, isTopMatch, height, lineColor)

    }
}

@Composable
fun LeftLineArea(isFirstColumn: Boolean, lineColor: Color) {
    if (!isFirstColumn) {
        Spacer(modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp * 0.05f)
            .height(2.dp)
            .background(lineColor))
    } else {
        Spacer(modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp * 0.05f))
    }
}

@Composable
fun RightLineArea(isLastColumn: Boolean, isTopMatch: Boolean, height: Dp, lineColor: Color) {
    if (!isLastColumn) {
        RightLine(isTopMatch, height, lineColor)
    } else {
        Spacer(modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp * 0.05f)
            .height(2.dp))
    }
}

@Composable
fun RightLine(isTopMatch: Boolean, height: Dp, lineColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp * 0.05f)
            .height(2.dp)
            .background(lineColor))

        if (isTopMatch) {
            TopMatchRightVerticalLine(height, lineColor)
        } else {
            BottomMatchRightVerticalLine(height, lineColor)
        }
    }
}

@Composable
fun TopMatchRightVerticalLine(height: Dp, lineColor: Color) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(modifier = Modifier.height(height / 2))
        Spacer(modifier = Modifier
            .width(2.dp)
            .height(height / 2 + 2.dp)
            .background(lineColor))
    }
}

@Composable
fun BottomMatchRightVerticalLine(height: Dp, lineColor: Color) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier
            .width(2.dp)
            .height(height / 2 + 2.dp)
            .background(lineColor))
        Spacer(modifier = Modifier.height(height / 2))
    }
}

@Composable
fun TopLabelArea() {
    // Define your top label area content here
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
        ,
        text = "Team",
        color = Color.White,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TeamScoreArea(team: String, score: Int) {
    // Define your team score area content here
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, color = Color.Gray)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            text = team,
        )

        Text(
            modifier = Modifier,
            text = score.toString(),
        )
    }
}

@Composable
fun TouchForMoreInfoArea() {
    // Define your touch for more info area content here
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
        ,
        text = "Touch for more info",
        textAlign = TextAlign.Center
    )
}


@Preview(showBackground = true)
@Composable
fun BracketCellPreview() {
    val previewMatchData = MatchData("Team 1", "Team 2", 2, 1)
    BracketCell(
        matchData = previewMatchData,
        heightScalingExponent = 0,
        isTopMatch = true,
        isCollapsed = false,
        isFirstColumn = true,
        isLastColumn = false
    )
}
