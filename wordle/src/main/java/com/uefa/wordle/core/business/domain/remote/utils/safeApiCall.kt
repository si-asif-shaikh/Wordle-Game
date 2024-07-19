package com.uefa.wordle.core.business.domain.remote.utils


import com.uefa.wordle.core.business.domain.ApiThrowable
import com.uefa.wordle.core.business.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal suspend fun <T : Any> safeApiCall(apiCall: suspend () -> Resource<T>): Resource<T> {

    return withContext(Dispatchers.IO) {
        try {
            apiCall.invoke()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is SocketTimeoutException -> {
                    Resource.Failure(ApiThrowable.NetworkError("Something went wrong!"))
                }

                is UnknownHostException -> {
                    Resource.Failure(ApiThrowable.NetworkError("Something went wrong!"))
                }

                is ConnectException -> {
                    Resource.Failure(ApiThrowable.NetworkError("You don't have a proper internet connection"))
                }


                is IOException -> {
                    Resource.Failure(ApiThrowable.NetworkError("Something went wrong!"))
                }

                is HttpException -> {
                    when (val code = throwable.code()) {
                        in 400..499 -> {
                            Resource.Failure(
                                ApiThrowable.ClientError(
                                    code, throwable.message()
                                )
                            )
                        }

                        in 500..599 -> {
                            Resource.Failure(ApiThrowable.ServerError(code, throwable.message()))
                        }

                        else -> {
                            Resource.Failure(ApiThrowable.NetworkError(throwable.message()))
                        }
                    }
                }

                else -> {
                    Resource.Failure(ApiThrowable.NetworkError(throwable.message.orEmpty()))
                }
            }
        }
    }
}