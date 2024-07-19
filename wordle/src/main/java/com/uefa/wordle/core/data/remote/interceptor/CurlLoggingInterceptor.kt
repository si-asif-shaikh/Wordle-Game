package com.si.corefantasy.data.remote.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.net.URLDecoder
import java.nio.charset.Charset
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurlLoggingInterceptor @Inject constructor(var tag: String) : Interceptor {
    private val UTF8 = Charset.forName("UTF-8")
    private var stringBuffer: StringBuffer? = null

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        this.stringBuffer = StringBuffer("")
        // add cURL command
        this.stringBuffer!!.append("cURL -g ")
        this.stringBuffer!!.append("-X ")
        // add method
        this.stringBuffer!!.append(request.method.uppercase(Locale.getDefault())).append(" ")
        // adding headers
        for (headerName in request.headers.names()) {
            addHeader(headerName, request.header(headerName))
        }

        // adding request body
        val requestBody = request.body
        if (request.body != null) {
            val buffer = Buffer()
            requestBody!!.writeTo(buffer)
            val contentType = requestBody.contentType()
            if (contentType != null) {
                addHeader("Content-Type", request.body!!.contentType()!!.toString())
                val charset = contentType.charset(UTF8)
                this.stringBuffer!!.append(" -d '")
                    .append(safeUrlDecoder(buffer.readString(charset!!))).append("'")
            }
        }

        // add request URL
        this.stringBuffer!!.append(" \"").append(request.url.toString()).append("\"")
        this.stringBuffer!!.append(" -L")

        CurlPrinter.print(tag, request.url.toString(), this.stringBuffer!!.toString())

        return chain.proceed(request)
    }

    private fun addHeader(headerName: String, headerValue: String?) {
        this.stringBuffer!!.append("-H " + "\"").append(headerName).append(": ").append(headerValue)
            .append("\" ")
    }
}

/**
 * This method help to safely decode text using utf-8
 * @return height in px
 */
internal fun safeUrlDecoder(text: String): String {
    var data = text
    try {
        data = data.replace("%(?![0-9a-fA-F]{2})".toRegex(), "%25")
        data = data.replace("\\+".toRegex(), "%2B")
        data = URLDecoder.decode(data, "utf-8")
    } catch (e: Exception) {
        data = text
    }
    return data
}

object CurlPrinter {
    /**
     * Drawing toolbox
     */
    private val SINGLE_DIVIDER = "────────────────────────────────────────────"

    private var sTag = "CURL"

    fun print(tag: String?, url: String, msg: String) {
        // setting tag if not null
        if (tag != null)
            sTag = tag

        val logMsg = StringBuilder("\n")
        logMsg.append("\n")
        logMsg.append(SINGLE_DIVIDER)
        logMsg.append("\n")
        logMsg.append(msg)
        logMsg.append(" ")
        logMsg.append(" \n")
        logMsg.append(SINGLE_DIVIDER)
        logMsg.append(" \n ")
        log(logMsg.toString())
    }

    private fun log(msg: String) {
        Log.d(sTag, msg)
    }
}