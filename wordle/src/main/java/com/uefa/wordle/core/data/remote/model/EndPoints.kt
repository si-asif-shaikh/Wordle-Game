package com.uefa.wordle.core.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EndPoints(
    @Json(name = "getHintsUrl")
    val getHintsUrl: String?,
    @Json(name = "getSubmittedWord")
    val getSubmittedWord: String?,
    @Json(name = "submitWordUrl")
    val submitWordUrl: String?
)