package id.maskology.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StoreRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStoreRemoteKeys(storeRemoteKeys: List<StoreRemoteKeys>)

    @Query("SELECT * FROM storeRemoteKeys WHERE id = :id")
    suspend fun getStoreRemoteKeysId(id: String): StoreRemoteKeys?

    @Query("DELETE FROM storeRemoteKeys")
    suspend fun deleteStoreRemoteKeys()
}