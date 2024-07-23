package com.uefa.wordle.core.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import android.content.Context
import android.content.SharedPreferences
import com.uefa.wordle.core.business.domain.prefs.PreferenceManager
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class CookieInterceptor @Inject constructor(
    private val preferenceManager: PreferenceManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        // Get the Set-Cookie header
        val setCookieHeader = response.headers("Set-Cookie")
        if (setCookieHeader.isNotEmpty()) {
            val cookies = setCookieHeader.joinToString(";")
            runBlocking {
                preferenceManager.setWordleToken(cookies)
            }
            // Save cookies to SharedPreferences
        }

        return response
    }
}
