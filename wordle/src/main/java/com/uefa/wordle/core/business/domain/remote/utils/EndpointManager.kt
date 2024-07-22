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

    fun getHintsUrl(tourGameDayId:String): String

    fun submitWordUrl(): String

    fun getSubmittedWord(): String

}

internal class EndpointManagerImpl @Inject constructor() : EndpointManager {

    companion object {

        val BASE_URL: String
            get() = FantasyRetrofitClient.getBaseUrl()

        val userId: String
            get() = "a-c59f-11ee-9dd4-0a2e0486673f"

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

    override fun getHintsUrl(tourGameDayId: String): String {
        return getBaseUrl() + config?.getHintsUrl
            ?.replace("{tourgamedayid}",tourGameDayId)
    }

    override fun submitWordUrl(): String {
        return getBaseUrl() + config?.submitWordUrl
            ?.replace("{userguid}",userId)
    }


    override fun getSubmittedWord(): String {
        return getBaseUrl() + config?.getSubmittedWord
            ?.replace("{userguid}",userId)
    }
}