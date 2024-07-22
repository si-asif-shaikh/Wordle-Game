package com.uefa.wordle.wordlegame.data.remote.service

import com.uefa.wordle.core.data.remote.model.BaseDataResponse
import com.uefa.wordle.core.data.remote.model.BaseResponse
import com.uefa.wordle.wordlegame.data.remote.model.SubmitWordResponseE
import com.uefa.wordle.wordlegame.data.remote.model.WordleHintsDetailsE
import com.uefa.wordle.wordlegame.data.remote.model.request.SubmitWordRequestE
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

internal interface WordleApiService {


    @GET
    suspend fun getHints(
        @Url url: String
    ) : BaseResponse<List<WordleHintsDetailsE>?>

    @POST
    suspend fun submitWord(
        @Url url: String,
        @Body body: SubmitWordRequestE
    ) : BaseDataResponse<SubmitWordResponseE?>

    @GET
    suspend fun getSubmittedWord(
        @Url url: String
    ) : BaseDataResponse<SubmitWordResponseE?>
}