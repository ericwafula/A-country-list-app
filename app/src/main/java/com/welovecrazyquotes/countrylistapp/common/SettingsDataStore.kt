package com.welovecrazyquotes.countrylistapp.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

const val DARK_MODE_PREFERENCES_NAME = "dark_mode_preferences_name"

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(
    name = DARK_MODE_PREFERENCES_NAME
)

/**
 *  handles saving and persisting darkMode state
 */
class SettingsDataStore(context: Context) {
    companion object {
        val IS_DARK_MODE_ENABLED = booleanPreferencesKey("is_dark_mode_enabled")
    }

    /**
     *  checks to see if darkMode is enabled
     */
    val dataStore = context.datastore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            }
            else {
                throw it
            }
        }
        .map { preferences ->
            preferences[IS_DARK_MODE_ENABLED] ?: false
        }

    /**
     *  saves darkMode options to preferences
     */
    suspend fun saveDarkModeToPreferences(isDarkModeEnabled: Boolean, context: Context) {
        context.datastore.edit { preferences ->
            preferences[IS_DARK_MODE_ENABLED] = isDarkModeEnabled
        }
    }
}