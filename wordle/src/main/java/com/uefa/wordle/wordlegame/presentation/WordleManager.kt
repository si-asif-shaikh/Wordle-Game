package com.uefa.wordle.wordlegame.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WordleManager @Inject constructor() {

    private var _currentGuess: String = ""
    val currentGuess: String get() = _currentGuess

    var targetWord: String = ""


    private var currentWordleGuessList: List<String> = listOf()

    private val _wordleGuessListState: MutableStateFlow<WordleUseResult> =
        MutableStateFlow(WordleUseResult())
    val wordleGuessListState
        get() = _wordleGuessListState.map {
            it.apply {
                _currentGuess = this.currentGuess
                currentWordleGuessList = this.guessList
            }
        }

    val keyboardState: Map<Char, LetterStatus> = ('A'..'Z').associateWith { LetterStatus.UNUSED }

    fun setup(target: String) {
        targetWord = target
    }

    fun addLetter(letter: Char) {
        _currentGuess += letter
//        val newGuesses =
//            if (currentWordleGuessList.isNotEmpty()) currentWordleGuessList.toMutableList().apply {
//                this[lastIndex] = _currentGuess
//            } else listOf<String>(_currentGuess)
        _wordleGuessListState.value = _wordleGuessListState.value.copy(
//            guessList = newGuesses,
            currentGuess = currentGuess
        )
    }

    fun removeLetter() {
        val currentGuess = _currentGuess.dropLast(1)
//        val newGuesses =
//            if (currentWordleGuessList.isNotEmpty()) currentWordleGuessList.toMutableList().apply {
//                this[lastIndex] = currentGuess
//            } else listOf<String>(currentGuess)

        _wordleGuessListState.value = _wordleGuessListState.value.copy(
//            guessList = newGuesses,
            currentGuess = currentGuess
        )
    }

    fun checkGuess() {
        val newGuesses = currentWordleGuessList + currentGuess
        val newKeyboardState = _wordleGuessListState.value.keyboardState.toMutableMap()

        currentGuess.forEachIndexed { index, letter ->
            when {
                targetWord[index].lowercase() == letter.lowercase() -> newKeyboardState[letter] =
                    LetterStatus.CORRECT

                targetWord.lowercase().contains(letter.lowercase()) -> newKeyboardState[letter] =
                    LetterStatus.PRESENT

                else -> newKeyboardState[letter] = LetterStatus.ABSENT
            }
        }

        _wordleGuessListState.value = _wordleGuessListState.value.copy(
            guessList = newGuesses,
            keyboardState = newKeyboardState,
            currentGuess = ""
        )
    }

    fun resetGame(){
        _wordleGuessListState.value = _wordleGuessListState.value.copy(
            guessList = listOf(),
            keyboardState = keyboardState,
            currentGuess = ""
        )
    }

    fun highlightSubmittedWord(userSubmitflag: List<Int>) {
        val newGuesses = currentWordleGuessList + currentGuess
        val newKeyboardState = _wordleGuessListState.value.keyboardState.toMutableMap()

        userSubmitflag.forEachIndexed{ index, state ->
            val letter = targetWord[index]
            when(state){
                WordState.WRONG_WORD.state -> {
                    newKeyboardState[letter] = LetterStatus.ABSENT
                }
                WordState.WORD_PRESENT.state -> {
                    newKeyboardState[letter] = LetterStatus.CORRECT
                }
                WordState.CORRECT_PLACE.state -> {
                    newKeyboardState[letter] = LetterStatus.PRESENT
                }
            }
        }

        _wordleGuessListState.value = _wordleGuessListState.value.copy(
            guessList = newGuesses,
            keyboardState = newKeyboardState,
            currentGuess = ""
        )
    }
}

data class WordleUseResult(
    val guessList: List<String> = listOf(),
    val keyboardState: Map<Char, LetterStatus> = ('A'..'Z').associateWith { LetterStatus.UNUSED },
    val currentGuess: String = ""
)

enum class LetterStatus {
    UNUSED, CORRECT, PRESENT, ABSENT
}

enum class WordState(val state:Int){
    WRONG_WORD(0),WORD_PRESENT(1),CORRECT_PLACE(2)
}