package com.uefa.wordle.core.data.local.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.squareup.moshi.Moshi
import com.uefa.wordle.core.business.domain.prefs.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal const val DATASTORE_NAME = "corefantasy-datastore"
internal val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)


class DataStoreManagerImpl @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val moshi: Moshi
    ) : PreferenceManager {

    private val dataStore = applicationContext.dataStore

    private val fantasyTokenKey = stringPreferencesKey("fantasy_token")


    override fun getFantasyToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[fantasyTokenKey]
        }.distinctUntilChanged()
    }
}