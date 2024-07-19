package com.uefa.wordle.core.business.domain.prefs

import kotlinx.coroutines.flow.Flow

internal interface PreferenceManager {

    fun getFantasyToken(): Flow<String?>
}