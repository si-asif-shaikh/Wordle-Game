package com.uefa.wordle.core.data.remote.interceptor

import com.uefa.wordle.core.business.domain.prefs.PreferenceManager
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

        val appToken = "_USC=MndyOUVtWVNsRXhsbWNTR0VsK2xzUnFJVFMxNzdZbWtKY2MvdGZSMzgyb3QySXNxMHN2QXVDRlNPc1NrbmhDR3dtMm5laDV4Z1RzS01LVEpReU9OeUxSb1lNcS9YdlpkZkFXb0cwcG9STFQyVGRiNmd6cjZrSTJsQWpxa3JHTXhCY0FsaTNQc1lDT2xkZ05Ccjg0bnZNZHFDM2xyVWt0alAxYTBnVVFhaGx3OGtrOGVqdittYUJCQm1PMnJINXlJNm43TE5kVXdGb0Z0YUtkN2RUcm1Bc2tmc29tc2QyNkVaVlFZN3JUdVZqbVRRaUYvQ2N4aS9sZHVMbHFTZENkQUNpUGNIOGJKdHljeDFZZGNVeUowVFFyTTlyR2NSdDMzejNFUmtwdGd3RThXeU91M01kcnhvRjI3clhpbjBQb1VleU9LcktwN0NORU9LRE8vemQzaDBQa2lxZ1pQN2pRNExJRTRVZWFNRUdFdHBSL09TZDkzNUhNNHc0WVYxZHJYSjJSL1RpS3g5SjQ5QXAxNWNPRXY2ZnhkcURjS1hud1puejdjb1BSVnA3emIvWVdTYTNuZDZCWDM0NFBpcDMrQlo0dFRPYTl1T1dtRnMyMmxTdlZlbFYyTWU2VWFFVVFJUXFxQXdTYW1INWdOb01KZ0NRWnNweHgyRURURUloZ2dyQW10Qk9IVUZWWGRoTXRBQnY0L09sM3phaWFocnBoVWNXNHllTi95UDBNbGx6TXN3Ri80c29LVzFjWUJKOU9lR3NTZ1hQSXRhT1N1RzNCUXIyTDdrODk1c01NY08yVFRHQXhRbHRQbmRNWT0%3D; _URC=%7B%22user_guid%22%3A%220eb43fc3-8351-44ce-be21-5b0f9a1d3748-23072024024520452%22%2C%22name%22%3A%22%22%2C%22first_name%22%3A%22asif%22%2C%22last_name%22%3A%22sk%22%2C%22email_id%22%3Anull%2C%22is_first_login%22%3A%220%22%2C%22favourite_club%22%3A%22%22%2C%22edition%22%3A%22%22%2C%22status%22%3A%221%22%2C%22is_custom_image%22%3A%220%22%2C%22social_user_image%22%3A%22https%3A%2F%2Flh3.googleusercontent.com%2Fa%2FAEdFTp661JxEh-3OAW_6Gioo46BDXlil_qEOPXro83Kx%3Ds96-c%22%2C%22profile_completion_percentage%22%3Anull%2C%22client_id%22%3Anull%2C%22guid%22%3A%22C2B008CF70E95A08A96FBACE6463EFD4F2AD2042%22%2C%22waf_user_guid%22%3A%2260990b3b-7e4d-48ed-bb3b-d3b8acedf441%22%7D;"
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
        if(shouldApplyAppToken(path))
            builder.addHeader("Cookie", appToken)

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