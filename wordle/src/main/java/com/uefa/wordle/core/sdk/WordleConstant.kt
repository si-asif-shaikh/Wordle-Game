package com.uefa.wordle.core.sdk

import com.uefa.wordle.core.sdk.Wordle.Companion.ENV_PROD


class WordleConstant {

    companion object {

        // config values
        const val LANGUAGE = "language"
        const val LOCALE = "locale"
        const val ENVIRONMENT = "environment"
        const val APP_VERSION = "app_version"

        private val SUPPORTED_LOCALES = listOf("en") //"de", "es", "fr", "it", "pt", "ru"

        private val _constant = HashMap<String, String>()
        val constant: Map<String, String>
            get() = _constant

        fun setup(
            env: String, versionName: String, locale: String
        ) {
            _constant[ENVIRONMENT] = env
            _constant[APP_VERSION] = versionName
            setLocale(locale)
        }

        private fun setLocale(locale: String) {
            if (locale.length >= 2) {
                val lang = locale.substring(0, 2)
                _constant[LANGUAGE] =
                    if (SUPPORTED_LOCALES.contains(lang)) lang else SUPPORTED_LOCALES[0]
                _constant[LOCALE] = locale
            }
        }

        fun getLanguageCode(): String {
            return WordleConstant.constant[WordleConstant.LANGUAGE] ?: "en"
        }

        fun getEnvironment(): String {
            return constant[ENVIRONMENT].orEmpty()
        }

        fun isProductionBuild(): Boolean{
            return constant[ENVIRONMENT].orEmpty() == ENV_PROD
        }
    }

}