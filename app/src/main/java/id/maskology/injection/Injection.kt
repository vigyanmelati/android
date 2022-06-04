package id.maskology.injection

import android.app.Application
import id.maskology.data.Repository
import id.maskology.data.local.database.MaskologyDatabase
import id.maskology.data.local.datastore.AuthPreferences
import id.maskology.data.remote.api.ApiConfig

object Injection {
    fun provideRepository(application: Application): Repository {
        val apiService = ApiConfig.getApiService()
        val maskologyDatabase = MaskologyDatabase.getInstance(application)
        val authPreferences = AuthPreferences.getInstance(application)
        return Repository.getInstance(apiService, maskologyDatabase, authPreferences)
    }
}