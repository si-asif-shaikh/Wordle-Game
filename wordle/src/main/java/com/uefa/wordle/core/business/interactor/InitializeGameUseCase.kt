package com.uefa.wordle.core.business.interactor

import android.content.Context
import com.uefa.wordle.core.business.Store
import com.uefa.wordle.core.business.domain.Resource
import com.uefa.wordle.core.business.repo.FeedRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

internal class InitializeGameUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val feedRepository: FeedRepository,
) {


    sealed class Result {

        data object Success : Result()

        data object Failure : Result()

    }

    suspend fun invoke(): Result{
        Store.init(context)
        when (feedRepository.getConfig()) {
            is Resource.Failure -> return Result.Failure
            is Resource.Success -> Unit
        }

//        //Wait for Store to update
//        try {
//            Store.config.filterNotNull().firstOrNull()
//        } catch (_: Exception) {
//        }

        return when(feedRepository.getTranslations()) {
            is Resource.Failure -> Result.Failure
            is Resource.Success -> Result.Success
        }

    }

}