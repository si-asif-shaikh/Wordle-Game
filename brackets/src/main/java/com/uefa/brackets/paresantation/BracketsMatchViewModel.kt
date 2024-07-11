package com.uefa.brackets.paresantation

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.uefa.brackets.utils.BaseViewModel
import com.uefa.brackets.utils.UiEffect
import com.uefa.brackets.utils.UiEvent
import com.uefa.brackets.utils.UiState
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