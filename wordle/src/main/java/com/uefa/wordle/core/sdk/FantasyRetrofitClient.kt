package com.uefa.wordle.core.sdk

import android.content.Context
import com.uefa.wordle.core.data.remote.interceptor.CurlLoggingInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.uefa.gaminghub.BuildConfig
import com.uefa.wordle.core.data.remote.service.FeedApiService
import com.uefa.wordle.core.di.NetworkModuleEntryPoint
import com.uefa.wordle.core.utils.DataJsonAdapterFactory
import com.uefa.wordle.wordlegame.data.remote.service.WordleApiService
import dagger.hilt.android.EntryPointAccessors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal class FantasyRetrofitClient {

    companion object {

        private lateinit var retrofit: Retrofit

        fun init(context: Context) {

            val httpClient = OkHttpClient.Builder()
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val requestInterceptor = EntryPointAccessors.fromApplication(
                context,
                NetworkModuleEntryPoint::class.java
            ).getRequestInterceptor()

            val responseInterceptor = EntryPointAccessors.fromApplication(
                context,
                NetworkModuleEntryPoint::class.java
            ).getResponseInterceptor()


            val busterInterceptor = EntryPointAccessors.fromApplication(
                context,
                NetworkModuleEntryPoint::class.java
            ).getBusterInterceptor()

            val cookieInterceptor = EntryPointAccessors.fromApplication(
                context,
                NetworkModuleEntryPoint::class.java
            ).getCookieInterceptor()


            httpClient.addInterceptor(requestInterceptor)
            httpClient.addInterceptor(busterInterceptor)
            httpClient.addInterceptor(cookieInterceptor)
//            httpClient.addInterceptor(responseInterceptor)

            if(BuildConfig.DEBUG) {
                httpClient.addInterceptor(loggingInterceptor)
                httpClient.addInterceptor(CurlLoggingInterceptor("coregaming:curl:"))
            }
            val moshi = Moshi.Builder()
//                .add(BaseDataResponseAdapterFactory())
                .add(DataJsonAdapterFactory())
                .add(KotlinJsonAdapterFactory())
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(httpClient.build())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        val feedApiService: FeedApiService by lazy {
            check(Companion::retrofit.isInitialized) { "Retrofit client is not initialized" }
            retrofit.create(FeedApiService::class.java)
        }

        val wordleApiService: WordleApiService by lazy {
            check(Companion::retrofit.isInitialized) { "Retrofit client is not initialized" }
            retrofit.create(WordleApiService::class.java)
        }


        fun getBaseUrl(): String {
            return when (WordleConstant.getEnvironment()) {
                Wordle.ENV_STG -> "https://stg-gujarat-titans.sportz.io"
                Wordle.ENV_PROD -> "https://www.gujarattitansipl.com"
                else -> "https://stg-gujarat-titans.sportz.io"
            }
        }


    }
}