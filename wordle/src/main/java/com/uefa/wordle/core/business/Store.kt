package com.uefa.wordle.core.business

import android.content.Context
import com.uefa.wordle.core.business.domain.model.Config
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class Store {

    companion object {

        fun init(context: Context) {

        }

        /**
         * This hold the game config and update itself whenever data is changed in cache data source
         */
        private val _config = MutableStateFlow<Config?>(null)
        val config: StateFlow<Config?> get() = _config

        /**
         * This field hold current value of the [config]
         */
        val currentConfig: Config? get() = _config.value

        private val _translation = MutableStateFlow<Map<String, String>?>(null)
        val translation: StateFlow<Map<String, String>?> get() = _translation

        val currentTranslation: Map<String, String>
            get() = _translation.value.orEmpty()


        fun saveGameConfigInMemory(config: Config) {
            _config.value = config
        }
    }
}