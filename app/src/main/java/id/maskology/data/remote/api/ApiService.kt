package id.maskology.data.remote.api

import id.maskology.data.model.Category
import id.maskology.data.model.Product
import id.maskology.data.model.Store
import id.maskology.data.remote.response.CategoryResponse
import id.maskology.data.remote.response.ProductResponse
import id.maskology.data.remote.response.StoreResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("category")
    suspend fun getAllCategory(
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : CategoryResponse

    @GET("store")
    suspend fun getAllStore(
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : StoreResponse

    @GET("product")
    suspend fun getAllProduct(
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : ProductResponse

    @GET("category/{id}")
    suspend fun getCategory(
        @Path("id") id: String
    ) : Category

    @GET("product/{id}")
    suspend fun getProduct(
        @Path("id") id: String
    ) : Product

    @GET("store/{id}")
    suspend fun getStore(
        @Path("id") id: String
    ) : Store
}