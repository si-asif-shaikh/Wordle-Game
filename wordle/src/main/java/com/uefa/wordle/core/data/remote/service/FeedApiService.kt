package com.uefa.wordle.core.data.remote.service

import com.uefa.wordle.core.data.remote.model.BaseDataResponse
import com.uefa.wordle.core.data.remote.model.LoginResponseE
import retrofit2.http.POST
import retrofit2.http.Url

internal interface FeedApiService {

    @POST
    suspend fun getLogin(
        @Url url: String
    ): BaseDataResponse<LoginResponseE?>

}