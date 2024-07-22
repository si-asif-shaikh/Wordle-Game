package com.uefa.wordle.wordlegame.business.domain.remote.model.request

import com.uefa.wordle.wordlegame.data.remote.model.SubmitWordResponseE
import com.uefa.wordle.wordlegame.data.remote.model.request.SubmitWordRequestE

data class SubmitWordRequest(
    val attemptNo: Int,
    val langCode: String,
    val platformId: Int,
    val tourGamedayId: String,
    val tourId: Int,
    val userHint: Int,
    val userID: Int,
    val userWord: String
)

fun SubmitWordRequest.toEntity() : SubmitWordRequestE{
    return SubmitWordRequestE(
        attemptNo = attemptNo,
        langCode = langCode,
        platformId = platformId,
        tourGamedayId = tourGamedayId,
        tourId = tourId,
        userHint = userHint,
        userID = userID,
        userWord = userWord
    )
}