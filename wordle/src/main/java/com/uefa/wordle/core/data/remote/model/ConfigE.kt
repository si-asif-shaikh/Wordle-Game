package com.uefa.wordle.core.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfigE(
    @Json(name = "baseDomain")
    val baseDomain: String?,
    @Json(name = "endPoints")
    val endPoints: EndPoints?
)