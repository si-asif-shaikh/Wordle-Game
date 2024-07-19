package com.uefa.wordle.core.data.remote.impl

import android.content.Context
import com.squareup.moshi.Moshi
import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.core.business.domain.model.Config
import com.uefa.wordle.core.business.domain.remote.FeedNetworkDataSource
import com.uefa.wordle.core.data.mapper.ConfigEntityToDomain
import com.uefa.wordle.core.data.remote.model.ConfigE
import com.uefa.wordle.core.data.remote.service.FeedApiService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class FeedNetworkDataSourceImpl @Inject constructor(
    private val moshi: Moshi,
    private val gameApiService: FeedApiService,
    @ApplicationContext private val context: Context,
) : FeedNetworkDataSource {

    override fun getConfig(): Resource<Config> {

        val response = loadAssetsFromFile("config.json")!!

        return Resource.Success(ConfigEntityToDomain().map(response))
    }

    override fun getTranslations(local: String): Resource<Map<String, String>> {
        return Resource.Success(emptyMap())
    }

//    fun toJson(type: ConfigE): String? {
//        val adapter: JsonAdapter<ConfigE> = moshi.adapter(ConfigE::class.java)
//        return try {
//            adapter.toJson(type)
//        } catch (e: Exception) {
//            null
//        }
//    }

    private fun fromJson(json: String): ConfigE? {
        return try {
            moshi.adapter(
                ConfigE::class.java
            ).fromJson(json)
        } catch (e: Exception) {
            null
        }
    }

    private fun loadAssetsFromFile(responseFile: String): ConfigE? {
        val inputStream = context.assets.open(responseFile)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        return fromJson(json)
    }
}