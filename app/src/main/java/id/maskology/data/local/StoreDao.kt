package id.maskology.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.maskology.data.model.Category
import id.maskology.data.model.Store

@Dao
interface StoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStore(story: List<Store>)

    @Query("SELECT * FROM category")
    fun getAllStore(): PagingSource<Int, Store>

    @Query("DELETE FROM store")
    suspend fun deleteAllStore()
}