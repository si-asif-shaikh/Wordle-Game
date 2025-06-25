package com.uefa.wordle.core.di

import com.uefa.wordle.core.business.domain.remote.FeedNetworkDataSource
import com.uefa.wordle.core.data.remote.impl.FeedNetworkDataSourceImpl
import com.uefa.wordle.wordlegame.business.domain.remote.WordleNetworkDataSource
import com.uefa.wordle.wordlegame.data.remote.impl.WordleNetworkDataSourceImpl
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

    @Binds
    fun provideWordleNetworkDataSource(
        wordleNetworkDataSourceImpl: WordleNetworkDataSourceImpl
    ): WordleNetworkDataSource
}