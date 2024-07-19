package com.uefa.wordle.core.di

import com.uefa.wordle.core.business.domain.remote.FeedNetworkDataSource
import com.uefa.wordle.core.data.remote.impl.FeedNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkDataSourceModule {

    @Binds
    fun provideFeedNetworkDataSource(
        feedNetworkDataSourceImpl: FeedNetworkDataSourceImpl
    ): FeedNetworkDataSource
}