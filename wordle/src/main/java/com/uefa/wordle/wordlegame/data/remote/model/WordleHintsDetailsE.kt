package com.uefa.wordle.wordlegame.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WordleHintsDetailsE(
    @Json(name = "finalHint")
    val finalHint: String?,
    @Json(name = "tourGamedayId")
    val tourGamedayId: Int?,
    @Json(name = "word")
    val word: String?,
    @Json(name = "wordLength")
    val wordLength: Int?
)