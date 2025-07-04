package com.uefa.wordle.wordlegame.data.remote.impl

import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.core.business.domain.prefs.PreferenceManager
import com.uefa.wordle.core.business.domain.remote.utils.EndpointManager
import com.uefa.wordle.core.business.domain.remote.utils.safeApiCall
import com.uefa.wordle.core.business.domain.remote.utils.toApiResult
import com.uefa.wordle.core.data.remote.model.DataValue
import com.uefa.wordle.wordlegame.business.domain.model.GetSubmitWordResponseType
import com.uefa.wordle.wordlegame.business.domain.remote.WordleNetworkDataSource
import com.uefa.wordle.wordlegame.business.domain.model.SubmitWordResponse
import com.uefa.wordle.wordlegame.business.domain.model.WordleHintsDetails
import com.uefa.wordle.wordlegame.business.domain.remote.model.request.SubmitWordRequest
import com.uefa.wordle.wordlegame.business.domain.remote.model.request.toEntity
import com.uefa.wordle.wordlegame.data.remote.mapper.SubmitWordResponseDMapper
import com.uefa.wordle.wordlegame.data.remote.service.WordleApiService
import com.uefa.wordle.wordlegame.presentation.LetterStatus
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

internal class WordleNetworkDataSourceImpl @Inject constructor(
    private val wordleApiService: WordleApiService,
    private val endpointManager: EndpointManager,
    private val submitWordResponseDMapper: SubmitWordResponseDMapper,
    private val preferenceManager: PreferenceManager
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
            val url = endpointManager.submitWordUrl(
                userId = preferenceManager.getUserGuId().firstOrNull().orEmpty()
            )

            val response = wordleApiService.submitWord(
                url = url,
                body = submitWordRequest.toEntity()
            )

            response.toApiResult {
                val dataValue = it
                when (dataValue) {
                    is DataValue.SingleValue -> {
                        val data = dataValue.value
                        data?.let {
                            submitWordResponseDMapper.map(it)
                        }
                    }

                    is DataValue.ValueList -> {
                        val data = dataValue.value?.firstOrNull()
                        data?.let {
                            submitWordResponseDMapper.map(it)
                        }
                    }

                    is DataValue.ErrorValue -> null
                }
            }
        }
    }

    override suspend fun getSubmittedWord(): Resource<GetSubmitWordResponseType> {
        return safeApiCall {
            val url = endpointManager.getSubmittedWord(
                userId = preferenceManager.getUserGuId().firstOrNull().orEmpty()
            )

            val response = wordleApiService.getSubmittedWord(
                url = url
            )

            response.toApiResult {
                val dataValue = it
                when (dataValue) {
                    is DataValue.SingleValue -> {
                        val data = dataValue.value
                        GetSubmitWordResponseType.NewGameResponse(data?.let {
                            submitWordResponseDMapper.map(it)
                        })
                    }

                    is DataValue.ValueList -> {
                        val data = dataValue.value
                        GetSubmitWordResponseType.LastGameResponse(data?.mapNotNull {
                            it?.let {
                                submitWordResponseDMapper.map(it)
                            }
                        })
                    }

                    is DataValue.ErrorValue -> null
                }
            }
        }
    }
}