package com.uefa.wordle.wordlegame.data.remote.impl

import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.core.business.domain.remote.utils.EndpointManager
import com.uefa.wordle.core.business.domain.remote.utils.safeApiCall
import com.uefa.wordle.core.business.domain.remote.utils.toApiResult
import com.uefa.wordle.wordlegame.business.domain.remote.WordleNetworkDataSource
import com.uefa.wordle.wordlegame.business.domain.model.SubmitWordResponse
import com.uefa.wordle.wordlegame.business.domain.model.WordleHintsDetails
import com.uefa.wordle.wordlegame.business.domain.remote.model.request.SubmitWordRequest
import com.uefa.wordle.wordlegame.business.domain.remote.model.request.toEntity
import com.uefa.wordle.wordlegame.data.remote.service.WordleApiService
import javax.inject.Inject

internal class WordleNetworkDataSourceImpl @Inject constructor(
    private val wordleApiService: WordleApiService,
    private val endpointManager: EndpointManager
) : WordleNetworkDataSource {

    override suspend fun getWordleHintDetails(tourGameDayId:String): Resource<WordleHintsDetails?> {

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
                }
            }
        }
    }

    override suspend fun submitWord(submitWordRequest: SubmitWordRequest): Resource<SubmitWordResponse?> {
        return safeApiCall {
            val url = endpointManager.submitWordUrl()

            val response = wordleApiService.submitWord(
                url = url,
                body = submitWordRequest.toEntity()
            )

            response.toApiResult {
                val data = it
                data.run {
                    SubmitWordResponse(
                        attemptNo = attemptNo?:0,
                        gdId = gdId?:0,
                        gtFlag = gtFlag?:0,
                        isHintuse = isHintuse?:0,
                        mastWord = mastWord.orEmpty(),
                        userAttemptNo = userAttemptNo?:0,
                        userPoint = userPoint?:0,
                        userSubmitflag = userSubmitflag?: listOf(),
                        userWord = userWord.orEmpty(),
                        wordLength = wordLength?:0
                    )
                }
            }
        }
    }
}