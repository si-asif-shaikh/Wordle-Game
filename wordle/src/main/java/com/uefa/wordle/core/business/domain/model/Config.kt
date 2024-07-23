package com.uefa.wordle.core.business.domain.model

data class Config(
    val totalGameAttempt: Int,
    val baseDomain: String,
    val loginUrl:String = "",
    val getHintsUrl: String = "",
    val submitWordUrl: String = "",
    val getSubmittedWord: String = "",
)
