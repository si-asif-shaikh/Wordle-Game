package com.uefa.wordle.core.business.domain.remote.utils


import com.uefa.wordle.core.sdk.FantasyRetrofitClient
import javax.inject.Inject


internal object ReplaceKeys {

}

interface EndpointManager {

    fun getConfigUrl(): String

    fun getTranslationsUrl(lang: String): String

}

internal class EndpointManagerImpl @Inject constructor() : EndpointManager {

    companion object {

        //        const val BASE_URL = "https://demo.sportz.io/gaming/temp-icc/"
        val BASE_URL: String
            get() = FantasyRetrofitClient.getBaseUrl()

    }

//    private val config: Config?
//        get() = Store.currentConfig

    private fun getBaseUrl(): String {
        return "config?.baseDomain ?: BASE_URL"
    }

    override fun getConfigUrl(): String {
        //This will have hardcoded URL because We should know its pointing before time and then leverage its content for formulating other URLs
//        return "${getBaseUrl()}classic/feeds/configs/app_config.json?v=1"
        return "${getBaseUrl()}/static-assets/tournament-fantasy/feeds/apps/app_config.json?v=2"
    }

    override fun getTranslationsUrl(lang: String): String {
//        return getBaseUrl() + config?.translationEndpointPath?.replace(KEY_LANGUAGE, lang).orEmpty()
        return ""
    }
}