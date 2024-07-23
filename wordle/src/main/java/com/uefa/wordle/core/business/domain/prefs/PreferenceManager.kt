package com.uefa.wordle.core.business.domain.prefs

import kotlinx.coroutines.flow.Flow

internal interface PreferenceManager {

    suspend fun setWordleToken(token: String)

    fun getWordleToken(): Flow<String?>

    suspend fun setUserGuId(userGuId: String)

    fun getUserGuId(): Flow<String?>
}