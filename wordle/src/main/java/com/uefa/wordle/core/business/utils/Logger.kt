package com.uefa.wordle.core.business.utils

import android.util.Log
import com.uefa.wordle.core.sdk.WordleConstant
import java.util.regex.Pattern

class Logger {

    abstract class Debug {


        private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")

        private val logWhenDebug = !WordleConstant.isProductionBuild()

        private val TAG = "GameLogger"

        internal data class Tag(val className: String, val methodName: String)

        private fun getTag(): Tag {
            val ste = Throwable().stackTrace
                .first { it.className !in listOf(Debug::class.java.name, Logger::class.java.name) }
            return if (ste != null) {
                var tag = ste.className.substringAfterLast('.')
                val m = ANONYMOUS_CLASS.matcher(tag)
                if (m.find()) {
                    tag = m.replaceAll("")
                }
                Tag(tag, ste.methodName)
            } else Tag(TAG, "")
        }

        open fun i(tag: String, message: String) {
            if (!logWhenDebug) return
            val methodName = getTag().methodName
            Log.i(TAG, "$tag: ${methodName}: $message")
        }

        open fun i(message: String) {
            if (!logWhenDebug) return
            val tag = getTag()
            Log.i(TAG, "$${tag.className}: ${tag.methodName}: $message")
        }

        open fun e(throwable: Throwable) {
            if (!logWhenDebug) return
            val tag = getTag()
            Log.e(
                TAG,
                "$${tag.className}: ${tag.methodName}: " + throwable.message.orEmpty(),
                throwable
            )
        }

        open fun e(tag: String, message: String) {
            if (!logWhenDebug) return
            val methodName = getTag().methodName
            Log.e(TAG, "$tag: ${methodName}: $message")
        }

        open fun e(tag: String, message: String, throwable: Throwable) {
            if (!logWhenDebug) return
            val methodName = getTag().methodName
            Log.e(TAG, "$tag: ${methodName}: $message", throwable)
        }

        open fun d(message: String) {
            if (!logWhenDebug) return
            val tag = getTag()
            Log.d(TAG, "${tag.className}: ${tag.methodName}: $message")
        }

        open fun d(tag: String, message: String) {
            if (!logWhenDebug) return
            val methodName = getTag().methodName
            Log.d(TAG, "$tag: ${methodName}: $message")
        }

    }

    companion object : Debug()
}