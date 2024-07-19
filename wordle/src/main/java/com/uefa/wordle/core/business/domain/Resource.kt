package com.uefa.wordle.core.business.domain

sealed interface Resource<in T : Any> {

    data class Success<T : Any>(val data: T?) : Resource<T>

    data class Failure(val throwable: ApiThrowable) : Resource<Any>


}


sealed class ApiThrowable(override val message: String) : Throwable() {

    data object NullDataError: ApiThrowable("")

    data class NetworkError(override val message: String) : ApiThrowable(message)

    data class ClientError(val code: Int, override val message: String) : ApiThrowable(message)

    data class ServerError(val code: Int, override val message: String) : ApiThrowable(message)

}
