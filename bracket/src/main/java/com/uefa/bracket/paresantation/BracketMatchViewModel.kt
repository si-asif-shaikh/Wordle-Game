package com.uefa.bracket.paresantation

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.uefa.bracket.bracket.Bracket
import com.uefa.bracket.bracket.MatchData
import com.uefa.bracket.utils.BaseViewModel
import com.uefa.bracket.utils.UiEffect
import com.uefa.bracket.utils.UiEvent
import com.uefa.bracket.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
internal class BracketMatchViewModel @javax.inject.Inject constructor(
private val savedStateHandle: SavedStateHandle,
): BaseViewModel<BracketMatchContract.Event, BracketMatchContract.State, BracketMatchContract.Effect>(savedStateHandle) {

    override fun createInitialState(): BracketMatchContract.State {
        return BracketMatchContract.State()
    }

    override fun handleEvent(event: BracketMatchContract.Event) {
        when (event) {
            else -> {}
        }
    }

}

internal class BracketMatchContract {

    sealed class Event() : UiEvent {

    }

    @kotlinx.parcelize.Parcelize
    data class State(
        val arg:String = "",
    ) : UiState {

        val rounds = listOf(
            Round(
                listOf(
                    Match("Team A", "Team B"),
                    Match("Team C", "Team D"),
                    Match("Team E", "Team F"),
                    Match("Team G", "Team H"),
                    Match("Team I", "Team J"),
                    Match("Team K", "Team L"),
                    Match("Team M", "Team N"),
                    Match("Team P", "Team Q"),
                )
            ),
            Round(
                listOf(
                    Match("Winner 1", "Winner 2"),
                    Match("Winner 3", "Winner 4"),
                    Match("Winner 5", "Winner 6"),
                    Match("Winner 7", "Winner 8"),
                )
            ),
            Round(
                listOf(
                    Match("Winner 9", "Winner 10"),
                    Match("Winner 11", "Winner 12"),
                )
            ),
            Round(
                listOf(
                    Match("Winner 13", "Winner 14")
                )
            )
        )

        val brackets = listOf(
            Bracket(
                name = "Eights", matches = listOf(
                    MatchData("Team 1", "Team 2", 3, 0),
                    MatchData("Team 3", "Team 4", 1, 2),
                    MatchData("Team 5", "Team 6", 2, 0),
                    MatchData("Team 7", "Team 8", 0, 3),
                    MatchData("Team 9", "Team 10", 1, 2),
                    MatchData("Team 11", "Team 12", 3, 1),
                    MatchData("Team 13", "Team 14", 2, 0),
                    MatchData("Team 15", "Team 16", 1, 2)
                )
            ),
            Bracket(
                name = "Quarter Finals", matches = listOf(
                    MatchData("Team 1", "Team 4", 3, 0),
                    MatchData("Team 5", "Team 8", 1, 2),
                    MatchData("Team 10", "Team 11", 2, 0),
                    MatchData("Team 13", "Team 16", 0, 3)
                )
            ),
            Bracket(
                name = "Semi Finals", matches = listOf(
                    MatchData("Team 1", "Team 8", 3, 0),
                    MatchData("Team 10", "Team 16", 1, 2)
                )
            ),
            Bracket(
                name = "Grand Finals", matches = listOf(
                    MatchData("Team 1", "Team 16", 1, 2)
                )
            )
        )

    }

    sealed class Effect() : UiEffect { }

}

@kotlinx.parcelize.Parcelize
internal data class Match(
    val team1:String,
    val team2:String,
    val winner: String? = null
) : Parcelable

@kotlinx.parcelize.Parcelize
internal data class Round(
    val matches: List<Match>
): Parcelable