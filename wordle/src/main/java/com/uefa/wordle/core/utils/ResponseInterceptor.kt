package com.uefa.wordle.core.utils

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

class ResponseInterceptor @Inject constructor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val responseBody = response.body
        if (responseBody != null) {
            val responseBodyString = responseBody.string()
            try {
                val jsonResponse = JSONObject(responseBodyString)
                val data = jsonResponse.optJSONObject("Data")
                data?.let {
                    val keys = it.keys()
                    while (keys.hasNext()) {
                        val key = keys.next()
                        if (it[key] is Int) {
                            it.put(key, JSONObject.NULL)
                        }
                    }
                    jsonResponse.put("Data", it)
                }

                val modifiedBody = jsonResponse.toString()
                    .toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())
                return response.newBuilder().body(modifiedBody).build()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            // If no modification is needed, return the original response
            val newResponseBody = responseBodyString.toResponseBody(responseBody.contentType())
            return response.newBuilder().body(newResponseBody).build()
        }

        return response
    }
}
