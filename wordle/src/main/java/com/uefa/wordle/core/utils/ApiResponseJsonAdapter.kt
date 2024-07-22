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
import com.uefa.wordle.core.data.remote.model.BaseDataResponse
import com.uefa.wordle.core.data.remote.model.Data
import com.uefa.wordle.core.data.remote.model.Meta
import com.uefa.wordle.wordlegame.data.remote.model.SubmitWordResponseE
import java.lang.reflect.Type

internal class BaseDataResponseJsonAdapter<T>(
    private val delegate: JsonAdapter<Data<T>>,
    private val moshi: Moshi
) : JsonAdapter<BaseDataResponse<T>>() {

    val TAG = "BaseDataResponseJsonAdapter"

    @FromJson
    override fun fromJson(reader: JsonReader): BaseDataResponse<T>? {
        var data: Data<T>? = null
        var meta: Meta? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "Data" -> {
                    val peek = reader.peekJson()
                    peek.beginObject()
                    while (peek.hasNext()) {
                        when (peek.nextName()) {
                            "Value" -> {
                                when (peek.peek()) {
                                    JsonReader.Token.BEGIN_OBJECT -> {
//                                        Log.d(TAG,"Response : ${reader.readJsonValue().toString()}")
                                        data = delegate.fromJson(reader)
                                    }

                                    JsonReader.Token.NUMBER -> {
                                        Log.d(TAG,"Error")
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
                    }
                    peek.endObject()

//                    data = delegate.fromJson(reader)

                }

                "Meta" -> {
//                    Log.d(TAG, "Meta : $reader")
                    meta = moshi.adapter(Meta::class.java).fromJson(reader)
                }

                else -> reader.skipValue()
            }
        }
        reader.endObject()

        if (data == null || meta == null) {
            throw JsonDataException("Expected Data and Meta but were missing at path ${reader.path}")
        }

        return BaseDataResponse(data, meta)
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: BaseDataResponse<T>?) {
        throw UnsupportedOperationException("BaseDataResponseJsonAdapter is only used to deserialize objects.")
    }
}

class BaseDataResponseAdapterFactory : JsonAdapter.Factory {
    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        if (Types.getRawType(type) != BaseDataResponse::class.java) {
            return null
        }

        val parameterizedType =
            Types.newParameterizedType(BaseDataResponse::class.java, Types.getRawType(type))
        val dataType = Types.newParameterizedType(Data::class.java, SubmitWordResponseE::class.java)
        val delegate = moshi.adapter<Data<Any?>>(dataType)

        return BaseDataResponseJsonAdapter(delegate, moshi = moshi)
    }
}
