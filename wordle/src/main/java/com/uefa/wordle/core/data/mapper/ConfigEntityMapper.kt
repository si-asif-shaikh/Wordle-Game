package com.uefa.wordle.core.data.mapper

import com.uefa.wordle.core.business.Mapper
import com.uefa.wordle.core.business.domain.model.Config
import com.uefa.wordle.core.data.remote.model.ConfigE

class ConfigEntityToDomain : Mapper<ConfigE, Config> {
    override fun map(from: ConfigE): Config {
        return from.run {
            Config(
                totalGameAttempt = totalGameAttempt?:6,
                baseDomain = baseDomain.orEmpty(),
                loginUrl = endPoints?.loginUrl.orEmpty(),
                getHintsUrl = endPoints?.getHintsUrl.orEmpty(),
                submitWordUrl = endPoints?.submitWordUrl.orEmpty(),
                getSubmittedWord = endPoints?.getSubmittedWord.orEmpty()
            )
        }
    }
}