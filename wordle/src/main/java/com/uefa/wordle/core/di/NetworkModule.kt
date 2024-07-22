package com.uefa.wordle.core.di

import com.squareup.moshi.Moshi
import com.uefa.wordle.core.business.domain.prefs.PreferenceManager
import com.uefa.wordle.core.business.domain.remote.utils.EndpointManager
import com.uefa.wordle.core.business.domain.remote.utils.EndpointManagerImpl
import com.uefa.wordle.core.data.remote.interceptor.RequestInterceptor
import com.uefa.wordle.core.data.remote.service.FeedApiService
import com.uefa.wordle.core.sdk.FantasyRetrofitClient
import com.uefa.wordle.core.utils.ResponseInterceptor
import com.uefa.wordle.wordlegame.data.remote.service.WordleApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideEndpointManager(): EndpointManager {
        return EndpointManagerImpl()
    }


    @Singleton
    @Provides
    fun provideFeedApiService(): FeedApiService {
        return FantasyRetrofitClient.feedApiService
    }

    @Singleton
    @Provides
    fun provideWordleApiService(): WordleApiService{
        return FantasyRetrofitClient.wordleApiService
    }

    @Singleton
    @Provides
    fun provideRequestInterceptor(preferenceManager: PreferenceManager): RequestInterceptor {
        return RequestInterceptor(preferenceManager)
    }

    @Singleton
    @Provides
    fun provideResponseInterceptor(preferenceManager: PreferenceManager): ResponseInterceptor {
        return ResponseInterceptor()
    }


}

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface NetworkModuleEntryPoint {

    fun getRequestInterceptor(): RequestInterceptor

    fun getResponseInterceptor(): ResponseInterceptor

}