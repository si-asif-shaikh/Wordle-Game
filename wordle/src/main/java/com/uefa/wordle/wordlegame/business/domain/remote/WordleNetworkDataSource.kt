package com.uefa.wordle.wordlegame.business.domain.remote

import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.wordlegame.business.domain.model.SubmitWordResponse
import com.uefa.wordle.wordlegame.business.domain.model.WordleHintsDetails
import com.uefa.wordle.wordlegame.business.domain.remote.model.request.SubmitWordRequest

internal interface WordleNetworkDataSource {

    suspend fun getWordleHintDetails(tourGameDayId:String) : Resource<WordleHintsDetails?>

    suspend fun submitWord(submitWordRequest: SubmitWordRequest) : Resource<SubmitWordResponse?>

}