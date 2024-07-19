package com.uefa.wordle.core.business.domain.model

data class Config(
    val baseDomain: String,
    val getHintsUrl: String = "",
    val submitWordUrl: String = "",
    val getSubmittedWord: String = "",
)
