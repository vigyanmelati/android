package id.maskology.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.maskology.data.model.Store

@Dao
interface StoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStore(store: List<Store>)

    @Query("SELECT * FROM store")
    fun getAllStore(): PagingSource<Int, Store>

    @Query("DELETE FROM store")
    suspend fun deleteAllStore()
}