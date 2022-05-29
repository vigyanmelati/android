package id.maskology.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategoryRemoteKeys(categoryRemoteKeys: List<CategoryRemoteKeys>)

    @Query("SELECT * FROM categoryRemoteKeys WHERE id = :id")
    suspend fun getCategoryRemoteKeysId(id: String): CategoryRemoteKeys?

    @Query("DELETE FROM categoryRemoteKeys")
    suspend fun deleteCategoryRemoteKeys()
}