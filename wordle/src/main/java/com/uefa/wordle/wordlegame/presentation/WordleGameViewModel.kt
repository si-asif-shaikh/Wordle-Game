package com.uefa.wordle.wordlegame.presentation

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.core.utils.BaseViewModel
import com.uefa.wordle.core.utils.UiEffect
import com.uefa.wordle.core.utils.UiEvent
import com.uefa.wordle.core.utils.UiState
import com.uefa.wordle.wordlegame.business.domain.remote.model.request.SubmitWordRequest
import com.uefa.wordle.wordlegame.business.repo.WordleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject


@HiltViewModel
internal class WordleGameViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val wordleManager: WordleManager,
    private val wordleRepository: WordleRepository,
    @ApplicationContext private val context: Context
) : BaseViewModel<WordleGameContract.Event, WordleGameContract.State, WordleGameContract.Effect>(
    savedStateHandle
) {

//    private val tourGamedayId = savedStateHandle.get<String>("tourGamedayId")

    override fun createInitialState(): WordleGameContract.State {
        return WordleGameContract.State()
    }

    override fun handleEvent(event: WordleGameContract.Event) {
        when (event) {
            WordleGameContract.Event.CheckGuess -> {
                if (uiState.isCheckEnable) {
//                    wordleManager.checkGuess()
                    submitWord()
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
        observeWordleList()
        fetchWordleGameDetails()
    }

    private fun fetchWordleGameDetails() {
        loader(true)
        viewModelScope.launch {
            val resource = wordleRepository.getSubmittedWord()
            when (resource) {
                is Resource.Failure -> {
                    context.showToast(resource.throwable.message)
                    loader(false)
                }

                is Resource.Success -> {
                    val data = resource.data
                    data?.let {
                        setState {
                            copy(
                                wordLength = it.wordLength,
                                gdId = it.gdId
                            )
                        }
//                        wordleManager.setup(target = it.word)
                        context.showToast(it.wordLength.toString())
                        loader(false)
                    }
                }
            }
        }
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

    private fun loader(value: Boolean){
        setState {
            copy(
                loader = value
            )
        }
    }

    private fun submitWord(){
        viewModelScope.launch {
            wordleRepository.submitWord(
                submitWordRequest = SubmitWordRequest(
                    attemptNo = 1,
                    langCode = "en",
                    platformId = 3,
                    tourGamedayId = uiState.gdId,
                    tourId = 1,
                    userHint = 1,
                    userID = 0,
                    userWord = uiState.currentGuess
                )
            )
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
        val gdId: String = "",
        val currentGuess: String = "",
        val wordLength: Int = 0,
        val guesses: List<String> = listOf(),
        val keyboardState: Map<Char, LetterStatus> = ('A'..'Z').associateWith { LetterStatus.UNUSED },
        val boosterList: List<String> = listOf(
            "Booster", "Booster"
        ),
        val loader: Boolean = false
    ) : UiState {

        val isCheckEnable = currentGuess.length == wordLength
    }

    sealed class Effect() : UiEffect {}

}

fun Context.showToast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}