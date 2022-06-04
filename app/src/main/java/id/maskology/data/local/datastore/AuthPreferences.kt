package id.maskology.data.local.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_pref")

class AuthPreferences(private val application: Application) :
    PreferencesInterface {

    override suspend fun saveAuthPreferences(email: String, name: String, imageUrl: String) {
        application.dataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
            preferences[USER_NAME] = name
            preferences[USER_IMAGE_URL] = imageUrl
        }
    }

    override suspend fun deleteAuthPreferences() {
        application.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    override fun getNamePreferences(): Flow<String> = application.dataStore.data.map { preferences ->
        preferences[USER_NAME] ?: ""
    }

    override fun getImageUrlPreferences(): Flow<String> = application.dataStore.data.map { preferences ->
        preferences[USER_IMAGE_URL] ?: ""
    }

    override fun getEmailPreferences(): Flow<String> = application.dataStore.data.map { preferences ->
        preferences[USER_EMAIL] ?: ""
    }

    companion object {
        val USER_EMAIL = stringPreferencesKey("USER_EMAIL")
        val USER_NAME = stringPreferencesKey("USER_NAME")
        val USER_IMAGE_URL = stringPreferencesKey("USER_IMAGE_URL")

        @Volatile
        private var INSTANCE: AuthPreferences? = null
        fun getInstance(application: Application): AuthPreferences {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AuthPreferences(application)
            }.also { INSTANCE = it }
        }
    }
}