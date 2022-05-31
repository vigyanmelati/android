package id.maskology.data.remote.response

import com.google.gson.annotations.SerializedName
import id.maskology.data.model.Product

data class ProductResponse(
    @field:SerializedName("meta")
    val meta: Meta,

    @field:SerializedName("data")
    val listProduct: List<Product>
)

data class Meta(
    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("totalPage")
    val totalPage: Int,

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("lastPage")
    val lastPage: Int,
)

