package id.maskology.data

import id.maskology.data.remote.api.ApiService

class Repository(apiService: ApiService) {
    companion object {
        private val TAG = Repository::class.java.simpleName

        @Volatile
        private var INSTANCE: Repository? = null
        fun getInstance(apiService: ApiService): Repository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Repository(apiService)
            }.also { INSTANCE = it }
    }
}