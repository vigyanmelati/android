package id.maskology.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.maskology.data.model.Category
import id.maskology.data.model.Product
import id.maskology.data.model.Store

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(story: List<Product>)

    @Query("SELECT * FROM category")
    fun getAllProduct(): PagingSource<Int, Product>

    @Query("DELETE FROM product")
    suspend fun deleteAllProduct()
}