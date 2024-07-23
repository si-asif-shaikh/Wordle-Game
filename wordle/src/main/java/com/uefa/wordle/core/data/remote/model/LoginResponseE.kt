package com.uefa.wordle.core.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseE(
    @Json(name = "ClientId")
    val clientId: String?,
    @Json(name = "CountryId")
    val countryId: String?,
    @Json(name = "CountryName")
    val countryName: String?,
    @Json(name = "FavTeamId")
    val favTeamId: String?,
    @Json(name = "FavTeamName")
    val favTeamName: String?,
    @Json(name = "FullName")
    val fullName: String?,
    @Json(name = "GUID")
    val gUID: String?,
    @Json(name = "IsActiveTour")
    val isActiveTour: Any?,
    @Json(name = "IsRegister")
    val isRegister: String?,
    @Json(name = "SocialId")
    val socialId: String?,
    @Json(name = "TeamId")
    val teamId: String?,
    @Json(name = "TeamName")
    val teamName: String?,
    @Json(name = "UserGuid")
    val userGuid: String?,
    @Json(name = "WAF_GUID")
    val wAFGUID: String?
)