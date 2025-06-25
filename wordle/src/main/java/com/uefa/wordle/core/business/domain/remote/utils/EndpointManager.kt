package com.uefa.wordle.core.business.domain.remote.utils


import com.uefa.wordle.core.business.Store
import com.uefa.wordle.core.business.domain.model.Config
import com.uefa.wordle.core.sdk.FantasyRetrofitClient
import javax.inject.Inject


internal object ReplaceKeys {

}

interface EndpointManager {

    fun getConfigUrl(): String

    fun getTranslationsUrl(lang: String): String

    fun getLoginUrl(): String

    fun getHintsUrl(tourGameDayId:String): String

    fun submitWordUrl(userId:String): String

    fun getSubmittedWord(userId:String): String

}

internal class EndpointManagerImpl @Inject constructor() : EndpointManager {

    companion object {

        val BASE_URL: String
            get() = FantasyRetrofitClient.getBaseUrl()

    }

    private val config: Config?
        get() = Store.currentConfig

    private fun getBaseUrl(): String {
        return config?.baseDomain ?: BASE_URL
    }

    override fun getConfigUrl(): String {
        //This will have hardcoded URL because We should know its pointing before time and then leverage its content for formulating other URLs
        return ""
    }

    override fun getTranslationsUrl(lang: String): String {
//        return getBaseUrl() + config?.translationEndpointPath?.replace(KEY_LANGUAGE, lang).orEmpty()
        return ""
    }

    override fun getLoginUrl(): String {
        return getBaseUrl() + config?.loginUrl
            ?.replace("{wafguid}","4CF4EF7C19CB3B8D230413D1841DA9B67CB72F47")
    }

    override fun getHintsUrl(tourGameDayId: String): String {
        return getBaseUrl() + config?.getHintsUrl
            ?.replace("{tourgamedayid}",tourGameDayId)
    }

    override fun submitWordUrl(userId:String): String {
        return getBaseUrl() + config?.submitWordUrl
            ?.replace("{userguid}",userId)
    }


    override fun getSubmittedWord(userId: String): String {
        return getBaseUrl() + config?.getSubmittedWord
            ?.replace("{userguid}",userId)
    }
}