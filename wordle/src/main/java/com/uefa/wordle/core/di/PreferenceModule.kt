package com.uefa.wordle.core.di


import com.uefa.wordle.core.business.domain.prefs.PreferenceManager
import com.uefa.wordle.core.data.local.prefs.DataStoreManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface PreferenceModule {

    @Binds
    fun providesPreferenceManager(dataStoreManagerImpl: DataStoreManagerImpl): PreferenceManager
}