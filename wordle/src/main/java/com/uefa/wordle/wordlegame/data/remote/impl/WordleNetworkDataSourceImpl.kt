package com.uefa.wordle.wordlegame.data.remote.impl

import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.core.business.domain.remote.utils.EndpointManager
import com.uefa.wordle.core.business.domain.remote.utils.safeApiCall
import com.uefa.wordle.core.business.domain.remote.utils.toApiResult
import com.uefa.wordle.core.data.remote.model.DataValue
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

    override suspend fun getWordleHintDetails(tourGameDayId: String): Resource<WordleHintsDetails?> {

        return safeApiCall {
            val url = endpointManager.getHintsUrl(tourGameDayId)

            val response = wordleApiService.getHints(
                url
            )

            response.toApiResult {
                val data = it?.firstOrNull()
                data?.let {
                    WordleHintsDetails(
                        finalHint = it.finalHint.orEmpty(),
                        tourGameDayId = it.tourGamedayId ?: 0,
                        word = it.word.orEmpty(),
                        wordLength = it.wordLength ?: 0
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
                val dataValue = it
                when (dataValue) {
                    is DataValue.SingleValue -> {
                        val data = dataValue.value
                        data?.run {
                            SubmitWordResponse(
                                attemptNo = attemptNo ?: attemptno ?: 0,
                                gdId = gdId ?: gdid ?: "",
                                gtFlag = gtFlag ?: 0,
                                isHintuse = isHintuse ?: ishintuse ?: 0,
                                mastWord = mastWord ?: mastword ?: "",
                                userAttemptNo = userAttemptNo ?: 0,
                                userPoint = userPoint ?: userpoint ?: 0,
                                userSubmitflag = userSubmitflag ?: usersubmitflag ?: listOf(),
                                userWord = userWord ?: userword ?: "",
                                wordLength = wordLength ?: wordlength ?: 0
                            )
                        }
                    }

                    is DataValue.ValueList -> {
                        val data = dataValue.value?.firstOrNull()
                        data?.run {
                            SubmitWordResponse(
                                attemptNo = attemptNo ?: attemptno ?: 0,
                                gdId = gdId ?: gdid ?: "",
                                gtFlag = gtFlag ?: 0,
                                isHintuse = isHintuse ?: ishintuse ?: 0,
                                mastWord = mastWord ?: mastword ?: "",
                                userAttemptNo = userAttemptNo ?: 0,
                                userPoint = userPoint ?: userpoint ?: 0,
                                userSubmitflag = userSubmitflag ?: usersubmitflag ?: listOf(),
                                userWord = userWord ?: userword ?: "",
                                wordLength = wordLength ?: wordlength ?: 0
                            )
                        }
                    }
                }
            }
        }
    }

    override suspend fun getSubmittedWord(): Resource<SubmitWordResponse> {
        return safeApiCall {
            val url = endpointManager.getSubmittedWord()

            val response = wordleApiService.getSubmittedWord(
                url = url
            )

            response.toApiResult {
                val dataValue = it
                when (dataValue) {
                    is DataValue.SingleValue -> {
                        val data = dataValue.value
                        data?.run {
                            SubmitWordResponse(
                                attemptNo = attemptNo ?: attemptno ?: 0,
                                gdId = gdId ?: gdid ?: "",
                                gtFlag = gtFlag ?: 0,
                                isHintuse = isHintuse ?: ishintuse ?: 0,
                                mastWord = mastWord ?: mastword ?: "",
                                userAttemptNo = userAttemptNo ?: 0,
                                userPoint = userPoint ?: userpoint ?: 0,
                                userSubmitflag = userSubmitflag ?: usersubmitflag ?: listOf(),
                                userWord = userWord ?: userword ?: "",
                                wordLength = wordLength ?: wordlength ?: 0
                            )
                        }
                    }

                    is DataValue.ValueList -> {
                        val data = dataValue.value?.firstOrNull()
                        data?.run {
                            SubmitWordResponse(
                                attemptNo = attemptNo ?: attemptno ?: 0,
                                gdId = gdId ?: gdid ?: "",
                                gtFlag = gtFlag ?: 0,
                                isHintuse = isHintuse ?: ishintuse ?: 0,
                                mastWord = mastWord ?: mastword ?: "",
                                userAttemptNo = userAttemptNo ?: 0,
                                userPoint = userPoint ?: userpoint ?: 0,
                                userSubmitflag = userSubmitflag ?: usersubmitflag ?: listOf(),
                                userWord = userWord ?: userword ?: "",
                                wordLength = wordLength ?: wordlength ?: 0
                            )
                        }
                    }
                }
            }
        }
    }
}