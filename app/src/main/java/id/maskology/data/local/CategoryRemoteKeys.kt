package id.maskology.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoryRemoteKeys")
data class CategoryRemoteKeys(
    @PrimaryKey
    val id: String,
    val prevKey: Int?,
    val nextKey: Int?
)
