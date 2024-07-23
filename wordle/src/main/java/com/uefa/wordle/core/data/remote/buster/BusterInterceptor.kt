package com.uefa.wordle.core.data.remote.buster

import com.uefa.wordle.core.business.utils.Logger
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

internal class BusterInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = performRequestBustingAndGetNewRequest(chain)
        return chain.proceed(newRequest);
    }

    /**
     * Method which will add buster to get api using [BusterData] and return new [Request]
     */
    private fun performRequestBustingAndGetNewRequest(chain: Interceptor.Chain): Request {
        val oldRequest = chain.request()
        val oldUrl = oldRequest.url
        val path = oldUrl.encodedPath
        val queryParameters = oldUrl.queryParameterNames
        return if (oldRequest.method == "GET" && shouldUseBuster(path, queryParameters)) {
            val buster = runBlocking { getBuster(path) }
            val url: HttpUrl = oldUrl.newBuilder().addQueryParameter("buster", buster).build()
            Logger.d(
                "REFRESH_TAG",
                "GET API with buster URL - ${url.toUrl()}"
            )
            oldRequest.newBuilder().url(url).build()
        } else {
            oldRequest.newBuilder().build()
        }
    }

    private fun shouldUseBuster(endPath: String?, queries: Set<String>): Boolean {
        return !queries.contains("buster") && endPath?.toBusterGetEndpointPath() != null
    }

    private fun String.toBusterGetEndpointPath(): GetEndpointPath? {
        val newPath = this.startsWith("/").let { if (!it) "/$this" else this }
        return GetEndpointPath.values()
            .find { newPath.contains(it.path.removePrefix("/"), true) }
    }

    private suspend fun getBuster(endPath: String?): String? {
        return endPath?.toBusterGetEndpointPath()?.let { BusterData.getBusterString(it) ?: BusterData.refreshBuster(it) }
    }
}