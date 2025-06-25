package com.uefa.wordle.core.business.domain.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class LoginResponse(
    val clientId: String,
    val countryId: String,
    val countryName: String,
    val favTeamId: String,
    val favTeamName: String,
    val fullName: String,
    val gUID: String,
    val isActiveTour: Any,
    val isRegister: String,
    val socialId: String,
    val teamId: String,
    val teamName: String,
    val userGuid: String,
    val wAFGUID: String
)