package com.uefa.wordle.core.business.repo

import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.core.business.domain.model.Config
import com.uefa.wordle.core.business.domain.remote.FeedNetworkDataSource
import javax.inject.Inject

internal class FeedRepository @Inject constructor(
    private val feedNetworkDataSource: FeedNetworkDataSource,
) {
    fun getConfig(): Resource<Config> {
        return feedNetworkDataSource.getConfig()
    }

    fun getTranslations(): Resource<Map<String, String>> {
        return feedNetworkDataSource.getTranslations("en")
    }
}