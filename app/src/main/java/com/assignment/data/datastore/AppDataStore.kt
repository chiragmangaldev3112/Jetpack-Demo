package com.assignment.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// used to store app cache
class AppDataStore(
    private val context: Context
) {
    // used to check whether user authenticated or not
    private val authenticated = booleanPreferencesKey("authenticated")

    companion object {
        // used to recognize log if used
        private const val TAG = "AppDataStore"
    }

    // used to store authentication state
    suspend fun storeAuthenticatedState(data: Boolean) {
        context.appDataStore.edit { preferences ->
            preferences[authenticated] = data
        }
    }

    // help to let know whether authenticated or not
    suspend fun readAuthenticatedState(): Boolean {
        return context.appDataStore.data.map { preferences ->
            preferences[authenticated]
        }.first() ?: false
    }


}

private val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(name = "assignment")
