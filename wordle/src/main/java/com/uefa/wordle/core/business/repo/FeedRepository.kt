package com.uefa.wordle.core.business.repo

import com.uefa.wordle.core.business.Store
import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.core.business.domain.model.Config
import com.uefa.wordle.core.business.domain.model.LoginResponse
import com.uefa.wordle.core.business.domain.prefs.PreferenceManager
import com.uefa.wordle.core.business.domain.remote.FeedNetworkDataSource
import javax.inject.Inject

internal class FeedRepository @Inject constructor(
    private val feedNetworkDataSource: FeedNetworkDataSource,
    private val preferenceManager: PreferenceManager
) {
    fun getConfig(): Resource<Config> {
        return feedNetworkDataSource.getConfig().apply {
            if (this is Resource.Success) {
                this.data?.let {
                    Store.saveGameConfigInMemory(it)
                }
            }

        }
    }

    fun getTranslations(): Resource<Map<String, String>> {
        return feedNetworkDataSource.getTranslations("en").apply {

        }
    }

    suspend fun getLogin(): Resource<LoginResponse?>{
        return feedNetworkDataSource.getLogin().apply {
                preferenceManager.setUserGuId(userGuId = data()?.gUID.orEmpty())
        }
    }
}