package id.maskology.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.*
import id.maskology.data.local.MaskologyDatabase
import id.maskology.data.local.ProductRemoteKeys
import id.maskology.data.model.Product
import id.maskology.data.remote.api.ApiConfig
import id.maskology.data.remote.api.ApiService
import id.maskology.data.remote.response.ProductResponse
import kotlinx.coroutines.flow.Flow

class Repository(
    private val apiService: ApiService,
    private val maskologyDatabase: MaskologyDatabase

) {
    fun getAllProduct(): Flow<PagingData<Product>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = ProductRemoteMediator(maskologyDatabase, apiService),
            pagingSourceFactory = {
                maskologyDatabase.productDao().getAllProduct()
            }
        ).flow
    }


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