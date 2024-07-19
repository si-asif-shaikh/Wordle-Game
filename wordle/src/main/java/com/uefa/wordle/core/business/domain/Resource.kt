package com.uefa.wordle.core.business.domain

sealed class Resource<out T> {

    data class Success<T>(val data: T?) : Resource<T>()

    data class Failure<T>(val throwable: ApiThrowable) : Resource<T>()


}


sealed class ApiThrowable(override val message: String) : Throwable() {

    data object NullDataError: ApiThrowable("Null Exception")

    data class NetworkError(override val message: String) : ApiThrowable(message)

    data class ClientError(val code: Int, override val message: String) : ApiThrowable(message)

    data class ServerError(val code: Int, override val message: String) : ApiThrowable(message)

}
