package com.uefa.wordle.wordlegame.data.remote.impl

import com.uefa.wordle.core.business.domain.ApiThrowable
import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.core.business.domain.remote.utils.EndpointManager
import com.uefa.wordle.core.business.domain.remote.utils.safeApiCall
import com.uefa.wordle.core.business.domain.remote.utils.toApiResult
import com.uefa.wordle.wordlegame.business.domain.remote.WordleNetworkDataSource
import com.uefa.wordle.core.data.remote.service.FeedApiService
import com.uefa.wordle.wordlegame.business.domain.model.WordleHintsDetails
import com.uefa.wordle.wordlegame.data.remote.service.WordleApiService
import javax.inject.Inject

internal class WordleNetworkDataSourceImpl @Inject constructor(
    private val wordleApiService: WordleApiService,
    private val endpointManager: EndpointManager
) : WordleNetworkDataSource {

    override suspend fun getWordleHintDetails(tourGameDayId:String): Resource<WordleHintsDetails> {

        return safeApiCall {
            val url = endpointManager.getHintsUrl(tourGameDayId)

            val response = wordleApiService.getHints(
                url
            )

            response.toApiResult {
                val data = it.firstOrNull()
                data?.let {
                    WordleHintsDetails(
                        finalHint = it.finalHint.orEmpty(),
                        tourGameDayId = it.tourGamedayId?:0,
                        word = it.word.orEmpty(),
                        wordLength = it.wordLength?:0
                    )
                }?: Resource.Failure(ApiThrowable.NullDataError)
            }
        }
    }
}