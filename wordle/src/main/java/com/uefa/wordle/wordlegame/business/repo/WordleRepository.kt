package com.uefa.wordle.wordlegame.business.repo

import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.wordlegame.business.domain.model.WordleHintsDetails
import com.uefa.wordle.wordlegame.business.domain.remote.WordleNetworkDataSource
import javax.inject.Inject

internal class WordleRepository @Inject constructor(
    private val wordleNetworkDataSource: WordleNetworkDataSource
) {

    suspend fun getWordleHintsDetails(tourGameDayId:String): Resource<WordleHintsDetails?>{
        return wordleNetworkDataSource.getWordleHintDetails(tourGameDayId)
    }

}