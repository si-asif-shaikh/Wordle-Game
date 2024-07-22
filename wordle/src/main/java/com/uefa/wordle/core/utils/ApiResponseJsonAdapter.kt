package com.uefa.wordle.core.utils

import android.util.Log
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.Types
import com.uefa.wordle.core.data.remote.model.ApiResponse
import com.uefa.wordle.core.data.remote.model.Data
import com.uefa.wordle.core.data.remote.model.Meta
import com.uefa.wordle.wordlegame.data.remote.model.SubmitWordResponseE
import java.lang.reflect.Type

internal class ApiResponseJsonAdapter<T>(
    private val delegate: JsonAdapter<Data<T>>,
    private val moshi: Moshi
) : JsonAdapter<ApiResponse<T>>() {

    val TAG = "ApiResponseJsonAdapter"

    @FromJson
    override fun fromJson(reader: JsonReader): ApiResponse<T>? {
        var data: Data<T>? = null
        var meta: Meta? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "Data" -> {
                    reader.beginObject()
                    when (reader.nextName()) {
                        "Value" -> {
                            when (reader.peek()) {
                                JsonReader.Token.BEGIN_OBJECT -> {
                                    data = delegate.fromJson(reader.readJsonValue().toString())
                                }

                                JsonReader.Token.NUMBER -> {

                                }

                                else -> {
                                    Log.d(
                                        TAG,
                                        "Expected BEGIN_OBJECT or NUMBER but was ${reader.peek()} at path ${reader.path}"
                                    )
                                }
                            }
                        }
                    }
                    reader.endObject()
                }

                "Meta" -> {
                    Log.d(TAG, "Meta : $reader")
                    meta = moshi.adapter(Meta::class.java).fromJson(reader)
                }

                else -> reader.skipValue()
            }
        }
        reader.endObject()

        if (data == null || meta == null) {
            throw JsonDataException("Expected Data and Meta but were missing at path ${reader.path}")
        }

        return ApiResponse(data, meta)
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: ApiResponse<T>?) {
        throw UnsupportedOperationException("ApiResponseJsonAdapter is only used to deserialize objects.")
    }
}

class ApiResponseAdapterFactory : JsonAdapter.Factory {
    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        if (Types.getRawType(type) != ApiResponse::class.java) {
            return null
        }

        val parameterizedType =
            Types.newParameterizedType(ApiResponse::class.java, Types.getRawType(type))
        val dataType = Types.newParameterizedType(Data::class.java, SubmitWordResponseE::class.java)
        val delegate = moshi.adapter<Data<Any?>>(dataType)

        return ApiResponseJsonAdapter(delegate, moshi = moshi)
    }
}
