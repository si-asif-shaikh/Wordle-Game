package com.uefa.wordle.core.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response data class is used in BusterInterceptor for parsing response and getting retVal.
 * It is not to be used for as response type for any Retrofit API service method.
 */
@JsonClass(generateAdapter = true)
internal data class BaseResponseTypeMeta(
    @Json(name = "Meta")
    val meta: Meta?
)

@JsonClass(generateAdapter = true)
internal data class BaseResponse<T>(
    @Json(name = "Data")
    val data: T?,
    @Json(name = "Meta")
    val meta: Meta,
)

@JsonClass(generateAdapter = true)
internal data class Data<T>(
    @Json(name = "FeedTime")
    val feedTime: Time?,
    @Json(name = "Value")
    val value: T?,
)

@JsonClass(generateAdapter = true)
internal data class Meta(
    @Json(name = "Message")
    val message: String?,
    @Json(name = "RetVal")
    val retVal: Int,
    @Json(name = "Success")
    val success: Boolean?,
    @Json(name = "Timestamp")
    val timestamp: Time?,
)

@JsonClass(generateAdapter = true)
internal data class Time(
    @Json(name = "ISTtime")
    val iSTtime: String?,
    @Json(name = "UTCtime")
    val uTCtime: String?,
)


internal fun BaseResponse<*>.isRetValOkay(): Boolean {
    return meta.retVal == 1
}