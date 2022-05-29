package id.maskology.data

import id.maskology.data.local.MaskologyDatabase
import id.maskology.data.remote.api.ApiService

class Repository(
    apiService: ApiService,
    maskologyDatabase: MaskologyDatabase

) {


    companion object {
        private val TAG = Repository::class.java.simpleName

        @Volatile
        private var INSTANCE: Repository? = null
        fun getInstance(apiService: ApiService, maskologyDatabase: MaskologyDatabase): Repository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Repository(apiService, maskologyDatabase)
            }.also { INSTANCE = it }
    }
}