package com.uefa.wordle.core.data.remote.interceptor

import com.uefa.wordle.core.business.domain.prefs.PreferenceManager
import com.uefa.wordle.core.sdk.FantasyRetrofitClient
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
        val cookie = "spid.89cd=e516721d-492b-49d2-969b-d5bdd90a03d5.1706690576.17.1720176950.1720174249.40647e8b-0923-4b4c-b98d-91366146cffd.a0d3d7c6-4d5f-48a8-9117-5f89323ef02b.225e6c38-c926-45ae-870c-a1f591446b00.1720176226730.2; _ga_KTW0GK5QPP=GS1.1.1720176955.13.0.1720176955.60.0.0; _ga=GA1.1.1974622820.1706690576; _fbp=fb.1.1706690576556.2016482554; is_used_data=1; is_shared_data=1; GTWordle_007=B6607F90601434168F70D018687F59B37CD9D1C9341C3EB63668E8B034E183AB3CD06756DBA1F8754255EE30526E7279F6FAAB4033890111D9A57C8ADB336C1995D3088255A81CE44C278A0B6DE50A906577A58C54A0557129556D9BC868EBFBCE9EB088C00ECF784F58553495ED1E3920A83C599736641C0EF8B2FD5353BC6885641D205BDD553A60C559DE1AE2D7A6769C37565014E98BE2AC41D1012C35C26223F8D3B4ACDA8ECFE091A586A7A91330B5FA5F9E417EE2CB1FDD7C8DB6178F2180EA06F32FECA306F4895D67538269D7D49D9DDFE6EA28435E4336B5DEB285C105CBE46A94DC9E1225713D9B1F2A7ACB89792C099E016064855449853B302E839A; USER_DATA=^%^7B^%^22attributes^%^22^%^3A^%^5B^%^7B^%^22key^%^22^%^3A^%^22USER_ATTRIBUTE_UNIQUE_ID^%^22^%^2C^%^22value^%^22^%^3A^%^22918652364527^%^22^%^2C^%^22updated_at^%^22^%^3A1720091723475^%^7D^%^5D^%^2C^%^22subscribedToOldSdk^%^22^%^3Afalse^%^2C^%^22deviceUuid^%^22^%^3A^%^22aab25a47-b3b5-434a-bcc2-6afe90e26a7b^%^22^%^2C^%^22deviceAdded^%^22^%^3Atrue^%^7D; SESSION=^%^7B^%^22sessionKey^%^22^%^3A^%^2270b09064-e962-4394-806a-44f8023ccb7c^%^22^%^2C^%^22sessionStartTime^%^22^%^3A^%^222024-07-05T09^%^3A44^%^3A08.859Z^%^22^%^2C^%^22sessionMaxTime^%^22^%^3A1800^%^2C^%^22customIdentifiersToTrack^%^22^%^3A^%^5B^%^5D^%^2C^%^22sessionExpiryTime^%^22^%^3A1720174448873^%^2C^%^22numberOfSessions^%^22^%^3A3^%^7D; OPT_IN_SHOWN_TIME=1720091617277; SOFT_ASK_STATUS=^%^7B^%^22actualValue^%^22^%^3A^%^22dismissed^%^22^%^2C^%^22MOE_DATA_TYPE^%^22^%^3A^%^22string^%^22^%^7D; moe_uuid=aab25a47-b3b5-434a-bcc2-6afe90e26a7b; ASP.NET_SessionId=be50jshopzlvq4ijxxgopnhr; _USC=RERJQzczN2FRMDNVRmM4WmpWNHN1QitFaU5GTXdsMjNhNU9GWExNOW5nR2pDWDBVQ2I0OXV4TnN4SnQrRzdmRVU1T2pjVFIwUnBBY3Jyc1dURmlaK3QzNVM4b2ZKM2tKbGMyWXJIMS9SOC91YWdlb0l5azdwSkNyUmRXTXAzNXVQYWM5K0RTZlg4YklvUzVhNmFoMW1rOExEQ3dRRGhVN2hpQnpQVzZ1S1lrSFhwL2JacWUwdFptV3h5YXM5U1dlQVVqTGg1L0hpb1IzNmNtNXRORWFRdzM3TklzNVlrSXpxQXFNZzI2ekpOK05pL1dpN0RyWFA5OTBFUE1yOHZEdXpKQzZFbjROdjYxNFRTcjYyTkl0eGE2MVFPb0E0NGY1Q1VIQWZVSFJ6YVJIWUFaZ0dRRzRzMHNEanpkTHZ1NkhmbVE0bEczNGFqSEhheGRONlV2U3E5RjFUck5vQlBYUExUVmVkMlFZSjdvS0MrWWc0K2lqMXNBUWx2MTI0WlRCRnRHc2RyOUFySDZKS0dyU1dEVGlFZz09; _URC=^%^7B^%^22user_guid^%^22^%^3A^%^223d36a70a-d86e-483b-bd94-ec410aa99e44-04072024111521493^%^22^%^2C^%^22name^%^22^%^3A^%^22^%^22^%^2C^%^22first_name^%^22^%^3A^%^22ADITYA^%^22^%^2C^%^22last_name^%^22^%^3A^%^22Pandey^%^22^%^2C^%^22email_id^%^22^%^3Anull^%^2C^%^22is_first_login^%^22^%^3A^%^220^%^22^%^2C^%^22favourite_club^%^22^%^3A^%^22^%^22^%^2C^%^22edition^%^22^%^3A^%^22^%^22^%^2C^%^22status^%^22^%^3A^%^221^%^22^%^2C^%^22is_custom_image^%^22^%^3A^%^221^%^22^%^2C^%^22social_user_image^%^22^%^3A^%^22^%^22^%^2C^%^22profile_completion_percentage^%^22^%^3Anull^%^2C^%^22client_id^%^22^%^3Anull^%^2C^%^22guid^%^22^%^3A^%^224CF4EF7C19CB3B8D230413D1841DA9B67CB72F47^%^22^%^2C^%^22waf_user_guid^%^22^%^3A^%^228c9351ea-b5a2-45d8-9ef1-c628b6524578^%^22^%^7D; GTWordle_RAW=^%^7B^%^0A^%^20^%^20^%^22GUID^%^22^%^3A^%^20^%^2242dba320-c59f-11ee-9dd4-0a2e0486673f^%^22^%^2C^%^0A^%^20^%^20^%^22WAF_GUID^%^22^%^3A^%^20^%^224CF4EF7C19CB3B8D230413D1841DA9B67CB72F47^%^22^%^2C^%^0A^%^20^%^20^%^22FullName^%^22^%^3A^%^20^%^22^%^22^%^2C^%^0A^%^20^%^20^%^22ClientId^%^22^%^3A^%^20^%^221^%^22^%^2C^%^0A^%^20^%^20^%^22UserGuid^%^22^%^3A^%^20^%^22^%^22^%^2C^%^0A^%^20^%^20^%^22TeamId^%^22^%^3A^%^20^%^22^%^22^%^2C^%^0A^%^20^%^20^%^22TeamName^%^22^%^3A^%^20^%^22^%^22^%^2C^%^0A^%^20^%^20^%^22CountryId^%^22^%^3A^%^20^%^22^%^22^%^2C^%^0A^%^20^%^20^%^22CountryName^%^22^%^3A^%^20^%^22^%^22^%^2C^%^0A^%^20^%^20^%^22FavTeamId^%^22^%^3A^%^20^%^22^%^22^%^2C^%^0A^%^20^%^20^%^22FavTeamName^%^22^%^3A^%^20^%^22^%^22^%^2C^%^0A^%^20^%^20^%^22IsActiveTour^%^22^%^3A^%^20null^%^2C^%^0A^%^20^%^20^%^22IsRegister^%^22^%^3A^%^20^%^22^%^22^%^2C^%^0A^%^20^%^20^%^22SocialId^%^22^%^3A^%^20^%^22EC25AB711567^%^22^%^0A^%^7D; si-wordle-mix-api-buster=20240705104259; spses.89cd=*"
        val token = runBlocking {
            preferenceManager.getFantasyToken().firstOrNull()
        }
        val builder = token?.let {
            addAppTokenHeader(chain, token)
        } ?: chain.request().newBuilder()

        // Add backdoor key
        builder.addHeader("Referer", "https://www.gujarattitansipl.com/wordle")
        builder.addHeader("entity", "$@nt0rYu")
        builder.addHeader("Cookie", cookie)
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