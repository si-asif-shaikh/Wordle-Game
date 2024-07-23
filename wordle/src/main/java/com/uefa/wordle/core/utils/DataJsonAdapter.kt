package com.uefa.wordle.core.utils

import android.util.Log
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.Types
import com.uefa.wordle.core.data.remote.model.BaseDataResponse
import com.uefa.wordle.core.data.remote.model.Data
import com.uefa.wordle.core.data.remote.model.DataValue
import com.uefa.wordle.core.data.remote.model.Meta
import com.uefa.wordle.core.data.remote.model.Time

internal class DataJsonAdapter<T>(
    private val valueType: Class<T>,
    private val moshi: Moshi
) : JsonAdapter<BaseDataResponse<T>>() {

    val TAG = "DataJsonAdapter"

    @FromJson
    override fun fromJson(reader: JsonReader): BaseDataResponse<T>? {
        var value: DataValue<T>? = null
        var feedTime: Time? = null
        var meta: Meta? = null

        try {
            reader.beginObject()
            while (reader.hasNext()) {
                when (reader.nextName()) {
                    "Data" -> {
                        val peek = reader.peekJson()
                        peek.beginObject()
                        while (peek.hasNext()) {
                            when (peek.nextName()) {
                                "Value" -> {
                                    Log.d(TAG, "Value Response")
                                    value = if (peek.peek() == JsonReader.Token.BEGIN_OBJECT) {
                                        val modelData = moshi.adapter<T>(valueType).fromJson(peek)
                                        Log.d(TAG, "object ${modelData.toString()}")
                                        DataValue.SingleValue(value = modelData)
                                    } else if (peek.peek() == JsonReader.Token.BEGIN_ARRAY) {
                                        val list = moshi.adapter<List<T>>(
                                            Types.newParameterizedType(
                                                List::class.java,
                                                valueType
                                            )
                                        ).fromJson(peek)
                                        Log.d(TAG, "list ${list.toString()}")
                                        DataValue.ValueList(value = list)
                                    } else {
                                        Log.d(TAG, "Value Null")
                                        null
                                    }
                                }

                                "FeedTime" -> {
                                    feedTime = moshi.adapter(Time::class.java).fromJson(peek)
                                    Log.d(TAG, "feed ${feedTime.toString()}")
                                }
                            }
                        }
                        peek.endObject()
                        reader.skipValue()
                    }

                    "Meta" -> {
                        meta = moshi.adapter(Meta::class.java).fromJson(reader)
                    }

                    else -> reader.skipValue()
                }
            }
            reader.endObject()
        } catch (e: Exception) {
            e.printStackTrace()
        }


        Log.d(TAG, "value ${value.toString()}")
        Log.d(TAG, "feedTime ${feedTime.toString()}")
        Log.d(TAG, "meta ${meta.toString()}")

        return BaseDataResponse(
            Data(value, feedTime),
            meta = meta
        )
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: BaseDataResponse<T>?) {
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
        throw UnsupportedOperationException("BaseDataResponseJsonAdapter is only used to deserialize objects.")
    }
}

