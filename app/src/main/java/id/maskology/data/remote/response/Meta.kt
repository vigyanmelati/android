package id.maskology.data.remote.response

import com.google.gson.annotations.SerializedName

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
