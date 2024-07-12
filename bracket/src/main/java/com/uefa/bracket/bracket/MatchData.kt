package com.uefa.bracket.bracket

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MatchDetailsView(matchData: MatchData) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TeamNamesArea(matchData)
        TeamScoresArea(matchData)
    }
}

@Composable
fun TeamNamesArea(matchData: MatchData) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Text(
            text = matchData.team1.uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = if (matchData.team1Score > matchData.team2Score) Color.Black else Color.Black.copy(alpha = 0.3f)
        )

        Text(
            text = "vs",
            fontSize = 16.sp
        )

        Text(
            text = matchData.team2.uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = if (matchData.team2Score > matchData.team1Score) Color.Black else Color.Black.copy(alpha = 0.3f)
        )
    }
}

@Composable
fun TeamScoresArea(matchData: MatchData) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = matchData.team1Score.toString().uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            color = if (matchData.team1Score > matchData.team2Score) Color.Black else Color.Black.copy(alpha = 0.3f)
        )

        Text(
            text = ":",
            fontSize = 16.sp
        )

        Text(
            text = matchData.team2Score.toString().uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            color = if (matchData.team2Score > matchData.team1Score) Color.Black else Color.Black.copy(alpha = 0.3f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MatchDetailsViewPreview() {
    MatchDetailsView(matchData = MatchData("Team 1", "Team 2", 3, 0))
}
