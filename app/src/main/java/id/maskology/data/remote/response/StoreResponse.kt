package id.maskology.data.remote.response

import com.google.gson.annotations.SerializedName
import id.maskology.data.model.Category
import id.maskology.data.model.Store

data class StoreResponse(
    @field:SerializedName("data")
    val listStore: List<Store>
)
