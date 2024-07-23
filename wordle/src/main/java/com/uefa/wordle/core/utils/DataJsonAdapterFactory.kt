package com.uefa.wordle.core.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.rawType
import com.uefa.wordle.core.data.remote.model.BaseDataResponse
import com.uefa.wordle.core.data.remote.model.Data
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class DataJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {
        if (Types.getRawType(type) == BaseDataResponse::class.java) {
            val parameterizedType = type as? ParameterizedType
            val valueType = parameterizedType?.actualTypeArguments?.get(0)?.rawType
            if (valueType != null) {
                val dataJsonAdapter = DataJsonAdapter(valueType, moshi)
                return dataJsonAdapter
            }
        }
        return null
    }
}