package com.uefa.wordle.wordlegame.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubmitWordResponseE(
    @Json(name = "attemptNo")
    val attemptNo: Int?,
    @Json(name = "gdId")
    val gdId: String?,
    @Json(name = "gtFlag")
    val gtFlag: Int?,
    @Json(name = "isHintuse")
    val isHintuse: Int?,
    @Json(name = "mastWord")
    val mastWord: String?,
    @Json(name = "UserAttemptNo")
    val userAttemptNo: Int?,
    @Json(name = "userPoint")
    val userPoint: Int?,
    @Json(name = "userSubmitflag")
    val userSubmitflag: List<Int>?,
    @Json(name = "userWord")
    val userWord: String?,
    @Json(name = "wordLength")
    val wordLength: Int?,

    @Json(name = "attemptno")
    val attemptno: Int?,
    @Json(name = "gdid")
    val gdid: String?,
    @Json(name = "ishintuse")
    val ishintuse: Int?,
    @Json(name = "mastword")
    val mastword: String?,
    @Json(name = "userpoint")
    val userpoint: Int?,
    @Json(name = "usersubmitflag")
    val usersubmitflag: List<Int>?,
    @Json(name = "userword")
    val userword: String?,
    @Json(name = "wordlength")
    val wordlength: Int?
)