package id.maskology.data.remote.response

import com.google.gson.annotations.SerializedName
import id.maskology.data.model.Product

data class ProductResponse(
    @field:SerializedName("listProduct")
    val listProduct: List<Product>
)
