package id.maskology.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.maskology.data.model.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: List<Category>)

    @Query("SELECT * FROM category")
    fun getAllCategory(): PagingSource<Int, Category>

    @Query("DELETE FROM category")
    suspend fun deleteAllCategory()
}