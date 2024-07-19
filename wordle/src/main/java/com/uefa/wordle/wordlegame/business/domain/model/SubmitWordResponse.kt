package com.uefa.wordle.wordlegame.business.domain.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class SubmitWordResponse(
    val attemptNo: Int,
    val gdId: Int,
    val gtFlag: Int,
    val isHintuse: Int,
    val mastWord: String?,
    val userAttemptNo: Int,
    val userPoint: Int,
    val userSubmitflag: List<Int>,
    val userWord: String,
    val wordLength: Int
)