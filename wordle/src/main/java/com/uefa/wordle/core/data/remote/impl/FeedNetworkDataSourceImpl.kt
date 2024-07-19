package com.uefa.wordle.core.data.remote.impl

import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.core.business.domain.model.Config
import com.uefa.wordle.core.business.domain.remote.FeedNetworkDataSource
import javax.inject.Inject

internal class FeedNetworkDataSourceImpl @Inject constructor(
) : FeedNetworkDataSource {

    override fun getConfig(): Resource<Config> {
        return Resource.Success(
            Config(
                ""
            )
        )
    }

    override fun getTranslations(local: String): Resource<Map<String, String>> {
        return Resource.Success(emptyMap())
    }
}