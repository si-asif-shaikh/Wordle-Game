package com.uefa.wordle.wordlegame.data.remote.model.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubmitWordRequestE(
    @Json(name = "attemptNo")
    val attemptNo: Int?,
    @Json(name = "langCode")
    val langCode: String?,
    @Json(name = "platformId")
    val platformId: Int?,
    @Json(name = "tourGamedayId")
    val tourGamedayId: String?,
    @Json(name = "tourId")
    val tourId: Int?,
    @Json(name = "userHint")
    val userHint: Int?,
    @Json(name = "userID")
    val userID: Int?,
    @Json(name = "userWord")
    val userWord: String?
)