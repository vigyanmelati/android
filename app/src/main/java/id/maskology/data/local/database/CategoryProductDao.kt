package id.maskology.data.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.maskology.data.model.Category
import id.maskology.data.model.CategoryProduct

@Dao
interface CategoryProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryProduct(categoryProduct: List<CategoryProduct>)

    @Query("SELECT * FROM categoryProduct")
    fun getAllCategoryProduct(): List<CategoryProduct>

    @Query("DELETE FROM categoryProduct")
    suspend fun deleteAllCategoryProduct()
}