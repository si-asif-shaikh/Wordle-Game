package com.uefa.bracket.bracket

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class MatchData(val team1: String, val team2: String, val team1Score: Int, val team2Score: Int)

data class Bracket(val name:String,val matches: List<MatchData>)

@Composable
fun BracketColumnView(
    bracket: Bracket,
    columnIndex: Int,
    focusedColumnIndex: Int,
    lastColumnIndex: Int
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(bracket.matches.size) { matchIndex ->


            BracketCell(
                matchData = bracket.matches[matchIndex],
                heightScalingExponent = columnIndex - focusedColumnIndex,
                isTopMatch = matchIndex % 2 == 0,
                isCollapsed = columnIndex < focusedColumnIndex,
                isFirstColumn = columnIndex == 0,
                isLastColumn = columnIndex == lastColumnIndex
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BracketColumnViewPreview() {
    val previewBracket = Bracket(
        name = "",
        matches = listOf(
            MatchData("Team 1", "Team 2", 2, 1),
            MatchData("Team 3", "Team 4", 3, 1),
            MatchData("Team 5", "Team 6", 1, 2),
            MatchData("Team 7", "Team 8", 0, 3)
        )
    )
    BracketColumnView(
        bracket = previewBracket,
        columnIndex = 0,
        focusedColumnIndex = 0,
        lastColumnIndex = 2
    )
}
