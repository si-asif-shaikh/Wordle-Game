package com.uefa.wordle.wordlegame.business.domain.remote

import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.wordlegame.business.domain.model.WordleHintsDetails

internal interface WordleNetworkDataSource {

    suspend fun getWordleHintDetails(tourGameDayId:String) : Resource<WordleHintsDetails>

}