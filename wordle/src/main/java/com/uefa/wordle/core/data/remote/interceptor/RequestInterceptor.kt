package com.uefa.wordle.core.data.remote.interceptor

import com.uefa.wordle.core.business.domain.prefs.PreferenceManager
import com.uefa.wordle.core.sdk.Wordle
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class RequestInterceptor(
    private val preferenceManager: PreferenceManager
): Interceptor {
    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val oldUrl = oldRequest.url
        val path = oldUrl.encodedPath

//        val cookie = "GTWordle_RAW=%7B%0A%20%20%22GUID%22%3A%20%229808c67a-4902-11ef-aecf-0e3f2a47a899%22%2C%0A%20%20%22WAF_GUID%22%3A%20%22C2B008CF70E95A08A96FBACE6463EFD4F2AD2042%22%2C%0A%20%20%22FullName%22%3A%20%22%22%2C%0A%20%20%22ClientId%22%3A%20%221%22%2C%0A%20%20%22UserGuid%22%3A%20%22%22%2C%0A%20%20%22TeamId%22%3A%20%22%22%2C%0A%20%20%22TeamName%22%3A%20%22%22%2C%0A%20%20%22CountryId%22%3A%20%22%22%2C%0A%20%20%22CountryName%22%3A%20%22%22%2C%0A%20%20%22FavTeamId%22%3A%20%22%22%2C%0A%20%20%22FavTeamName%22%3A%20%22%22%2C%0A%20%20%22IsActiveTour%22%3A%20null%2C%0A%20%20%22IsRegister%22%3A%20%22%22%2C%0A%20%20%22SocialId%22%3A%20%22EE26AE7B10%22%0A%7D;GTWordle_007=B6607F90601434168F70D018687F59B37CD9D1C9341C3EB63668E8B034E183AB3CD56359CBBFD07968579A7767665A709DA4A958229A0306CC9D6E88D56A235ADEE10BA858EB4A8D0A33831B7CE018895663B48C2BDE142548697D9F9E11A4E4A19CAA889B26BC2C0E0C340885E948406FA22C17C13A004942B4BED52007FD3CE95604240DA91439708B0FD27EB79BEA7AB45A1F1E62AAAAFEA7358A4D697ED05223E880A8ACDA8E908AEEE9CACB8E0B39FB8E1ED1452FB18D1DEF53E6CE44EE21F2CD04E71DF4E76D8BC019672FCE3BB685C2E39AEDBD685449043AB2CAB588950487AB6A92DF815B222661C251767BCB8C607E1DA644;"
        val token = runBlocking {
            preferenceManager.getWordleToken().firstOrNull()
        }
        val builder = token?.let {
            addAppTokenHeader(chain, token)
        } ?: chain.request().newBuilder()

        // Add backdoor key
        builder.addHeader("Referer", "https://www.gujarattitansipl.com/wordle")
        builder.addHeader("entity", "$@nt0rYu")
        if(shouldApplyAppToken(path) && !Wordle.appToken.isNullOrEmpty())
            builder.addHeader("Cookie", Wordle.appToken.orEmpty())

        if(!token.isNullOrEmpty())
            builder.addHeader("Cookie", token)

        return chain.proceed(builder.build())
    }

    private fun shouldApplyAppToken(endPath: String): Boolean{
        return endPath.contains("login")
    }


    private fun addAppTokenHeader(chain: Interceptor.Chain, token: String): Request.Builder {
        val oldRequest = chain.request()
        val oldUrl = oldRequest.url
        val headers = oldRequest.headers
        val path = with(oldUrl.encodedPath) {
            startsWith("/").let { if (!it) "/$this" else this }
        }

        if(path.contains("/services/user"))
            if(headers["x-icc-token"] == null) {
                return oldRequest.newBuilder().addHeader("x-icc-token", token)
            }

        return oldRequest.newBuilder()
    }
}