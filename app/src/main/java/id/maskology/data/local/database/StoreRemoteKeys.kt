package id.maskology.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storeRemoteKeys")
data class StoreRemoteKeys(
    @PrimaryKey
    val id: String,
    val prevKey: Int?,
    val nextKey: Int?
)
