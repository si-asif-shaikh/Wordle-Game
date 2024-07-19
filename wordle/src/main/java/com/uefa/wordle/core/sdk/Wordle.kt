package com.uefa.wordle.core.sdk

import android.app.Application
import android.content.Context
import androidx.annotation.RestrictTo
import androidx.annotation.StringDef

class Wordle {

    @StringDef(ENV_PROD, ENV_STG)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Environment

    companion object {

        const val ENV_PROD = "prod"
        const val ENV_STG = "stg"

        private lateinit var application: Application
        @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
        fun getContext(): Application {
            return application
        }

        fun init(
            context: Context,
            @Environment environment: String,
            versionName: String,
            locale: String = "en"
        ) {
            application = context.applicationContext as Application
            FantasyConstant.setup(environment, versionName, locale)
//            FantasyAuthManager.init()
            FantasyRetrofitClient.init(application)
        }
    }

}