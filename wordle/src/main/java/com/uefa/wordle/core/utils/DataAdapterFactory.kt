package com.uefa.wordle.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.rawType
import com.uefa.wordle.core.data.remote.model.ApiResponse
import com.uefa.wordle.core.data.remote.model.ResponseData
import java.lang.reflect.Type

internal class DataAdapterFactory : JsonAdapter.Factory {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        val rawType = Types.getRawType(type)

        if (rawType == ApiResponse::class.java) {
            val dataType = Types.newParameterizedType(ResponseData::class.java)
            val successDataType = moshi.adapter<Any>(dataType)
            return DataAdapter(rawType)
        }
        return null
    }
}
