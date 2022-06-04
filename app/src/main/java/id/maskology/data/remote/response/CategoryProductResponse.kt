package id.maskology.data.remote.response

import com.google.gson.annotations.SerializedName
import id.maskology.data.model.Category
import id.maskology.data.model.CategoryProduct

data class CategoryProductResponse(
    @field:SerializedName("meta")
    val meta: Meta,

    @field:SerializedName("data")
    val listCategoryProduct: List<CategoryProduct>
)


