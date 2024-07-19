package com.uefa.wordle.core.business.domain.remote

import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.core.business.domain.model.Config

internal interface FeedNetworkDataSource {
    fun getConfig(): Resource<Config>

    fun getTranslations(local: String): Resource<Map<String, String>>
}