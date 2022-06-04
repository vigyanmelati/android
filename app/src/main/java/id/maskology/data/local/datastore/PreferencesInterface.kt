package id.maskology.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface PreferencesInterface {
    suspend fun saveAuthPreferences(email: String, name: String, imageUrl: String){}
    suspend fun deleteAuthPreferences()
    fun getNamePreferences(): Flow<String>
    fun getEmailPreferences(): Flow<String>
    fun getImageUrlPreferences(): Flow<String>
}