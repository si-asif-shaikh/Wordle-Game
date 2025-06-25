package com.uefa.wordle.core.business.domain.remote

import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.core.business.domain.model.Config
import com.uefa.wordle.core.business.domain.model.LoginResponse

internal interface FeedNetworkDataSource {
    fun getConfig(): Resource<Config>

    fun getTranslations(local: String): Resource<Map<String, String>>

    suspend fun getLogin(): Resource<LoginResponse?>
}