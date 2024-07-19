package com.uefa.wordle.wordlegame.data.remote.service

import com.uefa.wordle.core.data.remote.model.BaseResponse
import com.uefa.wordle.wordlegame.data.remote.model.WordleHintsDetailsE
import retrofit2.http.GET
import retrofit2.http.Url

internal interface WordleApiService {


    @GET
    suspend fun getHints(
        @Url url: String
    ) : BaseResponse<List<WordleHintsDetailsE>>

}