package id.maskology.injection

import android.app.Application
import id.maskology.data.Repository
import id.maskology.data.remote.api.ApiConfig

object Injection {
    fun provideRepository(application: Application): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}