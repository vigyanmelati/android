package id.maskology.data

import android.util.Log
import androidx.paging.*
import id.maskology.data.local.MaskologyDatabase
import id.maskology.data.model.Category
import id.maskology.data.model.Product
import id.maskology.data.model.Store
import id.maskology.data.remote.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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

    fun getAllCategory(): Flow<PagingData<Category>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = CategoryRemoteMediator(maskologyDatabase, apiService),
            pagingSourceFactory = {
                maskologyDatabase.categoryDao().getAllCategory()
            }
        ).flow
    }

    fun getStore(id: String): Flow<Result<Store>>{
        return flow {
            emit(Result.Loading)
            try {
                val response = apiService.getStore(id)
                maskologyDatabase.storeDao().insertOneStore(response)
                Log.d(TAG, "Get Store: ${response.id}")
            }catch (e: Exception) {
                emit(Result.Error("Store Not Found"))
                Log.e(TAG, "Get Store: ${e.message.toString()}")
            }
            val store = maskologyDatabase.storeDao().getStore(id)
            Result.Success(store)
        }.flowOn(Dispatchers.IO)
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