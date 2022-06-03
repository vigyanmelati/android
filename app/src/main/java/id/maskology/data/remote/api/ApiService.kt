package id.maskology.data.remote.api

import id.maskology.data.model.Category
import id.maskology.data.model.Product
import id.maskology.data.model.Store
import id.maskology.data.remote.response.CategoryResponse
import id.maskology.data.remote.response.PredictResponse
import id.maskology.data.remote.response.ProductResponse
import id.maskology.data.remote.response.StoreResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("categories")
    suspend fun getAllCategory(
        @Query("page") page: Int,
        @Query("limit") size: Int
    ) : CategoryResponse

    @GET("stores")
    suspend fun getAllStore(
        @Query("page") page: Int,
        @Query("limit") size: Int
    ) : StoreResponse

    @GET("products")
    suspend fun getAllProduct(
        @Query("page") page: Int,
        @Query("limit") size: Int
    ) : ProductResponse

    @GET("categories/{id}")
    suspend fun getCategory(
        @Path("id") id: String
    ) : Category

    @GET("products/{id}")
    suspend fun getProduct(
        @Path("id") id: String
    ) : Product

    @GET("stores/{id}")
    suspend fun getStore(
        @Path("id") id: String
    ) : Store

    @Multipart
    @POST("predict")
    fun predict(
        @Part image: MultipartBody.Part
    ) : Call<PredictResponse>
}