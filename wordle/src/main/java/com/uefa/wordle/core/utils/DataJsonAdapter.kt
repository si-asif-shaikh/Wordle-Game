package com.uefa.wordle.core.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.Types
import com.uefa.wordle.core.business.utils.Logger
import com.uefa.wordle.core.data.remote.model.DataValue
import com.uefa.wordle.core.data.remote.model.Meta
import com.uefa.wordle.core.data.remote.model.MultiTypeBaseDataResponse
import com.uefa.wordle.core.data.remote.model.MultiTypeData
import com.uefa.wordle.core.data.remote.model.Time

internal class DataJsonAdapter<T>(
    private val valueType: Class<T>,
    private val moshi: Moshi
) : JsonAdapter<MultiTypeBaseDataResponse<T>>() {

    val TAG = "DataJsonAdapter"

    @FromJson
    override fun fromJson(reader: JsonReader): MultiTypeBaseDataResponse<T>? {
        var value: DataValue<T>? = null
        var feedTime: Time? = null
        var meta: Meta? = null

        try {
            reader.beginObject()
            while (reader.hasNext()) {
                when (reader.nextName()) {
                    "Data" -> {
                        if (reader.peek() == JsonReader.Token.NULL) {
                            reader.skipValue()
                        } else {
                            val peek = reader.peekJson()
                            peek.beginObject()
                            while (peek.hasNext()) {
                                when (peek.nextName()) {
                                    "Value" -> {
//                                    Logger.d(TAG, "Value Response")
                                        value = if (peek.peek() == JsonReader.Token.BEGIN_OBJECT) {
                                            val modelData =
                                                moshi.adapter<T>(valueType).fromJson(peek)
//                                        Logger.d(TAG, "object ${modelData.toString()}")
                                            DataValue.SingleValue(value = modelData)
                                        } else if (peek.peek() == JsonReader.Token.BEGIN_ARRAY) {
                                            val list = moshi.adapter<List<T>>(
                                                Types.newParameterizedType(
                                                    List::class.java,
                                                    valueType
                                                )
                                            ).fromJson(peek)
//                                        Logger.d(TAG, "list ${list.toString()}")
                                            DataValue.ValueList(value = list)
                                        } else if (peek.peek() == JsonReader.Token.NUMBER) {
                                            peek.skipValue()
//                                        val ratVal = reader.nextInt()
                                            DataValue.ErrorValue(null)
                                        } else {
                                            peek.skipValue()
//                                        Logger.d(TAG, "Value Null")
                                            null
                                        }
                                    }

                                    "FeedTime" -> {
                                        if (peek.peek() == JsonReader.Token.NULL) {
                                            peek.skipValue()
                                        } else {
                                            feedTime = moshi.adapter(Time::class.java).fromJson(peek)
                                        }
//                                        Logger.d(TAG, "feed ${feedTime.toString()}")
                                    }

                                    else -> peek.skipValue()
                                }
                            }
                            peek.endObject()
                            reader.skipValue()
                        }
                    }

                    "Meta" -> {

                        if (reader.peek() == JsonReader.Token.NULL) {
                            reader.skipValue()
                        } else {
                            meta = moshi.adapter(Meta::class.java).fromJson(reader)
                        }

                    }

                    else -> reader.skipValue()
                }
            }
            reader.endObject()
        } catch (e: Exception) {
            e.printStackTrace()
        }


        Logger.d(TAG, "value ${value.toString()}")
        Logger.d(TAG, "feedTime ${feedTime.toString()}")
        Logger.d(TAG, "meta ${meta.toString()}")

        return MultiTypeBaseDataResponse(
            MultiTypeData(value, feedTime),
            meta = meta
        )
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: MultiTypeBaseDataResponse<T>?) {
//        writer.beginObject()
//        writer.name("Value")
//        if (value?.data?.value is List<*>) {
//            moshi.adapter<List<T>>(Types.newParameterizedType(List::class.java, valueType)).toJson(writer, value.data.value as List<T>)
//        } else {
//            moshi.adapter<T>(valueType).toJson(writer, value?.data?.value)
//        }
//        writer.name("FeedTime")
//        moshi.adapter(Time::class.java).toJson(writer, value?.data?.feedTime)
//        writer.endObject()
        throw UnsupportedOperationException("MultiTypeBaseDataResponseJsonAdapter is only used to deserialize objects.")
    }
}

