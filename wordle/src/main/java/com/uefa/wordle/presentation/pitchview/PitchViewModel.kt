package com.uefa.wordle.presentation.pitchview

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PitchViewModel : ViewModel() {
    // Initial empty pitch with nulls

    private val _state = MutableStateFlow(PitchUiState())
    val state: StateFlow<PitchUiState> get() = _state
    val currentState: PitchUiState
        get() = state.value

    fun selectPosition(position: PlayerPosition) {
        setState {
            copy(
                selectedPosition = position
            )
        }
    }

    fun addPlayer(player: Player) {
        val position = currentState.selectedPosition
        if (position != null) {
            setState {
                copy(
                    selectedPosition = null,
                    pitch = pitch.toMutableMap().apply {
                        this[position] = player
                    }
                )
            }
        }

    }

    fun addBenchPlayers() {
        val currentPitch = currentState.pitch.toMutableMap()
//        val benchRow = currentState.pitchTotalRow.toList().map {
//            it.second.first()
//        }.toMap()
//
//        benchRow.forEach { playerPosition, player ->
//            currentPitch[playerPosition] = player?.copy(
//                isOnBench = true
//            )
//        }

        // Create a bench row from the current pitch total row
        val benchRow = currentState.pitchTotalRow.map { (position, players) ->
            players.first().first to players.first().second?.copy(isOnBench = true)
        }.toMap()

        // Add the bench players to the current pitch
        currentPitch.putAll(benchRow)

        setState {
            copy(
                pitch = currentPitch
            )
        }
    }

    protected fun setState(reduce: PitchUiState.() -> PitchUiState) {
        _state.update {
            currentState.reduce()
        }
    }

}

data class PitchUiState(
    val pitch: Map<PlayerPosition, Player?> = mutableMapOf(
        PlayerPosition(Skills.Forward.id, 0) to null,
        PlayerPosition(Skills.Forward.id, 1) to null,
        PlayerPosition(Skills.Forward.id, 2) to null,

        PlayerPosition(Skills.Midfielder.id, 0) to null,
        PlayerPosition(Skills.Midfielder.id, 1) to null,
        PlayerPosition(Skills.Midfielder.id, 2) to null,
        PlayerPosition(Skills.Midfielder.id, 3) to null,
        PlayerPosition(Skills.Midfielder.id, 4) to null,

        PlayerPosition(Skills.Defenders.id, 0) to null,
        PlayerPosition(Skills.Defenders.id, 1) to null,
        PlayerPosition(Skills.Defenders.id, 2) to null,
        PlayerPosition(Skills.Defenders.id, 3) to null,
        PlayerPosition(Skills.Defenders.id, 4) to null,

        PlayerPosition(Skills.Goalkeeper.id, 0) to null,
        PlayerPosition(Skills.Goalkeeper.id, 1) to null,
    ) ,
    val selectedPosition: PlayerPosition? = null
) {

    val pitchTotalRow = pitch.toList().groupBy { it.first.skill }
    val pitchBenchPlayer = pitch.toList()
        .filter {
            it.second?.isOnBench == true
        }

}

enum class Skills(val id: Int, val type: String) {
    Forward(1, "Forward"),
    Midfielder(2, "Midfielder"),
    Defenders(3, "Defenders"),
    Goalkeeper(4, "Goalkeeper"),
}

fun Int.toSkills(): String {
    return Skills.entries.find { it.id == this }?.type ?: "empty"
}
