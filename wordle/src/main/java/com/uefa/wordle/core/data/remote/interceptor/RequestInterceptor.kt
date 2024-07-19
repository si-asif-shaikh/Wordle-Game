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

//        val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InNpLTEifQ.eyJFeHRlcm5hbEF1dGhvcml6YXRpb25zQ29udGV4dERhdGEiOiJJTkQiLCJTdWJzY3JpcHRpb25TdGF0dXMiOiJpbmFjdGl2ZSIsIlN1YnNjcmliZXJJZCI6Ijk5Nzc3MTAwMTAwMyIsIkZpcnN0TmFtZSI6IlRlc3QtOTk3NzcxMDAxMDAzIiwiTGFzdE5hbWUiOiJGMSIsIlNlc3Npb25JZCI6ImV5SmhiR2NpT2lKb2RIUndPaTh2ZDNkM0xuY3pMbTl5Wnk4eU1EQXhMekEwTDNodGJHUnphV2N0Ylc5eVpTTm9iV0ZqTFhOb1lUSTFOaUlzSW5SNWNDSTZJa3BYVkNJc0ltTjBlU0k2SWtwWFZDSjkuZXlKaWRTSTZJak14T0NJc0luTnBJam9pTVRRd09UWmpORGN0WTJKa1lTMDBZMlF6TFRreU0yVXRORGM1TkROaU9EUTFZVEJsSWl3aWFIUjBjRG92TDNOamFHVnRZWE11ZUcxc2MyOWhjQzV2Y21jdmQzTXZNakF3TlM4d05TOXBaR1Z1ZEdsMGVTOWpiR0ZwYlhNdmJtRnRaV2xrWlc1MGFXWnBaWElpT2lJMU5EVXdPVEV6SWl3aWFXUWlPaUpoTVRRMVlqTTJZeTB6T0RjNUxUUXlabVV0WVRNek1pMWhOR1V3WkRabU9UZzRNelFpTENKMElqb2lNU0lzSW13aU9pSmxiaTFIUWlJc0ltUmpJam9pTVNJc0ltRmxaQ0k2SWpJd01qTXRNREl0TVRoVU1UUTZORFU2TlRNdU9EVXhXaUlzSW1SMElqb2lNU0lzSW1Wa0lqb2lNakF5TXkwd015MHdObFF4TkRvME5UbzFNeTQ0TlRGYUlpd2lZMlZrSWpvaU1qQXlNeTB3TWkwd05WUXhORG8wTlRvMU15NDROVEZhSWl3aWFYQWlPaUl4TnpVdU1UQXdMakU0T0M0ek1DSXNJbU1pT2lKTlZVMUNRVWtpTENKemRDSTZJazFJSWl3aWNHTWlPaUkwTURBd09UTWlMQ0pqYnlJNklrbE9SQ0lzSW01aVppSTZNVFkzTlRVeU1UazFNeXdpWlhod0lqb3hOamM0TVRFek9UVXpMQ0pwYzNNaU9pSmhjMk5sYm1SdmJpNTBkaUlzSW1GMVpDSTZJbUZ6WTJWdVpHOXVMblIySW4wLlg0QUx6Q2VXWDhxU0JocEowYU42a1prVldFM0k0VU14eHZXYXVTV3FrUHciLCJTdWJzY3JpYmVkUHJvZHVjdCI6IiIsImp0aSI6IjNmM2NjZDc0LWYyYjAtNDYyYS05MWVhLWQ4NzIxZDBmOWViYSIsImlhdCI6MTcwOTYzODUxMywiZXhwIjoxNzcyNzUzNzEzfQ.N4vAx-lHKUCG_bhOkqdbQERU2Kh7kuZtDLrYIP8R_-2ar-cbj2MvG1__8tQjod0ncMbCr1JmrqDYX3OKdWcRik1FSiUB2DXWJ9igEgDrw6GVX7aJ8qr2_05LxHWladojJU7-wjfdlRR1khs09xudd4HEfRnq5N8lQHa2BfdFqxN61MeWP4xSGnw9iE8LVyKklEw5sJduXVH7lQPiRuRLIcKyb_XCWBs1Sk3jQg7xTvXYGNwsBpkPgaqGZIU20RTDGQK-CH4CWy7RbiqUzToHak-M6ufnuXsh_tmwcjswbIQ8Im9605927KBMXYgbA-RuYw4PlO0tnkSUu69I4FuwGg"

        val token = runBlocking {
            preferenceManager.getFantasyToken().firstOrNull()
        }
        val builder = token?.let {
            addAppTokenHeader(chain, token)
        } ?: chain.request().newBuilder()


        // Add backdoor key
        builder.addHeader("entity", "ed0t4n$3!")
        return chain.proceed(builder.build())
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