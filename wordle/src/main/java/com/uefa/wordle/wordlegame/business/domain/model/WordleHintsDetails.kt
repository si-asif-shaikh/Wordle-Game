package com.uefa.wordle.wordlegame.business.domain.model


data class WordleHintsDetails(
    val finalHint: String,
    val tourGameDayId: Int,
    val word: String,
    val wordLength: Int
)