package com.uefa.wordle.wordlegame.business.domain.model


import com.uefa.wordle.wordlegame.presentation.LetterStatus

internal data class SubmitWordResponse(
    val attemptNo: Int,
    val gdId: String,
    val gtFlag: Int,
    val isHintuse: Int,
    val mastWord: String?,
    val userAttemptNo: Int,
    val userPoint: Int,
    val userSubmitflag: List<Pair<Char,LetterStatus>>,
    val userWord: String,
    val wordLength: Int,
    val isGameFinished: Boolean,
    val isWinner: Boolean
)

internal sealed class GetSubmitWordResponseType{

    data class NewGameResponse(
        val submitWordResponse: SubmitWordResponse?
    ) : GetSubmitWordResponseType()

    data class LastGameResponse(
        val lastGameWordsResponse: List<SubmitWordResponse>?
    ): GetSubmitWordResponseType()
}