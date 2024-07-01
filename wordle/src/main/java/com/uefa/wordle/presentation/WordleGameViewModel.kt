package com.uefa.wordle.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.uefa.wordle.utils.BaseViewModel
import com.uefa.wordle.utils.UiEffect
import com.uefa.wordle.utils.UiEvent
import com.uefa.wordle.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject


@HiltViewModel
internal class WordleGameViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val wordleManager: WordleManager
) : BaseViewModel<WordleGameContract.Event, WordleGameContract.State, WordleGameContract.Effect>(
    savedStateHandle
) {

    override fun createInitialState(): WordleGameContract.State {
        return WordleGameContract.State()
    }

    override fun handleEvent(event: WordleGameContract.Event) {
        when (event) {
            WordleGameContract.Event.CheckGuess -> {
                if (uiState.isCheckEnable) {
                    wordleManager.checkGuess()
                }
            }

            is WordleGameContract.Event.OnLetterEnter -> {
                wordleManager.addLetter(letter = event.letter)
            }

            WordleGameContract.Event.OnBackPressed -> {
                wordleManager.removeLetter()
            }
        }
    }

    init {
        wordleManager.setup(target = uiState.targetWord)
        observeWordleList()
    }

    private fun observeWordleList() {
        viewModelScope.launch {
            wordleManager.wordleGuessListState.collect {
                setState {
                    copy(
                        guesses = it.guessList,
                        keyboardState = it.keyboardState,
                        currentGuess = it.currentGuess
                    )
                }
            }
        }
    }
}

internal class WordleGameContract {

    sealed class Event() : UiEvent {

        object OnBackPressed : Event()
        data class OnLetterEnter(val letter: Char) : Event()
        object CheckGuess : Event()

    }

    @Parcelize
    data class State(
        val currentGuess: String = "",
        val targetWord: String = "Hello",
        val guesses: List<String> = listOf(),
        val keyboardState: Map<Char, LetterStatus> = ('A'..'Z').associateWith { LetterStatus.UNUSED },
        val boosterList: List<String> = listOf(
            "Booster", "Booster"
        ),
    ) : UiState {

        val isCheckEnable = currentGuess.length ==targetWord.length
    }

    sealed class Effect() : UiEffect {}

}