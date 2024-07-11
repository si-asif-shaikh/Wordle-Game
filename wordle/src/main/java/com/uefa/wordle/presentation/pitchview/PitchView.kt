package com.uefa.wordle.presentation.pitchview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PitchView(pitchViewModel: PitchViewModel = viewModel()) {
    val state by pitchViewModel.state.collectAsState()
    val pitch = state.pitch
    val selectedPosition = state.selectedPosition

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val boxSize = screenWidth / 6 // Adjust the number 6 based on your layout requirements
    val boxSpacing = 8.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
            .padding(boxSpacing),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.pitchTotalRow.forEach { (skills, pitchData) ->

            Row(
                horizontalArrangement = Arrangement.spacedBy(boxSpacing),
                modifier = Modifier.padding(bottom = boxSpacing),
            ) {

                pitchData.forEach { (playerPosition, _) ->

                    val player = pitch[playerPosition]

                    if (player == null || (!player.isOnBench)) {
                        Box(
                            modifier = Modifier
                                .size(boxSize)
                                .border(1.dp, Color.Black)
                                .clickable {
                                    pitchViewModel.selectPosition(playerPosition)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (player != null) {
                                Text(text = player.name)
                            } else {
                                Text(text = playerPosition.skill.toSkills())
                            }
                        }
                    }
                }
            }

        }

        if (state.pitchBenchPlayer.isNotEmpty()) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(boxSpacing),
                modifier = Modifier.padding(bottom = boxSpacing),
            ) {

                state.pitchBenchPlayer.forEach { (playerPosition, _) ->

                    val player = pitch[playerPosition]
                    Box(
                        modifier = Modifier
                            .size(boxSize)
                            .border(1.dp, Color.Black)
                            .clickable {
                                pitchViewModel.selectPosition(playerPosition)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (player != null) {
                            Text(text = player.name)
                        } else {
                            Text(text = playerPosition.skill.toSkills())
                        }
                    }
                }
            }
        }

        selectedPosition?.let {
            AddPlayerForm(onAddPlayer = { player ->
                pitchViewModel.addPlayer(player)
            })
        }

        // Submit Button
        Button(
            onClick = {
                pitchViewModel.addBenchPlayers()
            },
            modifier = Modifier.padding(top = boxSpacing)
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun AddPlayerForm(onAddPlayer: (Player) -> Unit) {
    var playerName by remember { mutableStateOf("") }

    Column {
        TextField(
            value = playerName,
            onValueChange = { playerName = it },
            label = { Text("Player Name") },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Button(onClick = {
            onAddPlayer(Player(playerName))
        }) {
            Text("Add Player")
        }
    }
}



