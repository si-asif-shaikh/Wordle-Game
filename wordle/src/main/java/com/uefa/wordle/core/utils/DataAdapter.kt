package com.uefa.wordle.core.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import com.uefa.wordle.core.data.remote.model.ErrorData
import com.uefa.wordle.core.data.remote.model.GenericSuccessData
import com.uefa.wordle.core.data.remote.model.ResponseData


internal class DataAdapter<T>(
    private val successDataType: Class<T>
) : JsonAdapter<ResponseData<T>>() {
    @FromJson
   override fun fromJson(reader: JsonReader): ResponseData<T> {
        var successData: GenericSuccessData<T>? = null
        var errorData: ErrorData? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "Value" -> {
                    val peek = reader.peekJson()
                    peek.beginObject()
                    while (peek.hasNext()) {
                        val name = peek.nextName()
                        if (name == "gdId") {
                            successData = GenericSuccessData(reader.readJsonValue() as T/*, readFeedTime(reader)*/)
                            break
                        } else {
                            errorData = reader.readJsonValue() as ErrorData
                            break
                        }
                    }
                    peek.close()
                }
                else -> reader.skipValue()
            }
        }
        reader.endObject()

        return successData?.let { ResponseData.Success(it) } ?: ResponseData.Error(errorData!!)
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: ResponseData<T>?) {
        writer.beginObject()
        when (value) {
            is ResponseData.Success -> {
                writer.name("Value")
                writer.jsonValue(value.value)
            }
            is ResponseData.Error -> {
                writer.name("Value")
                writer.jsonValue(value.value)
            }

            null -> TODO()
        }
        writer.endObject()
    }

//    private fun readFeedTime(reader: JsonReader): FeedTime {
//        var utcTime = ""
//        var istTime = ""
//        var cestTime = ""
//
//        reader.beginObject()
//        while (reader.hasNext()) {
//            when (reader.nextName()) {
//                "UTCTime" -> utcTime = reader.nextString()
//                "ISTTime" -> istTime = reader.nextString()
//                "CESTTime" -> cestTime = reader.nextString()
//                else -> reader.skipValue()
//            }
//        }
//        reader.endObject()
//
//        return FeedTime(utcTime, istTime, cestTime)
//    }
}
