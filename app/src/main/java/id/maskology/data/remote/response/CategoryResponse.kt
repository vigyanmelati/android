package id.maskology.data.remote.response

import com.google.gson.annotations.SerializedName
import id.maskology.data.model.Category

data class CategoryResponse(
    @field:SerializedName("data")
    val listCategory: List<Category>
)
