package id.maskology.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.maskology.data.model.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: List<Product>)

    @Query("SELECT * FROM product")
    fun getAllProduct(): PagingSource<Int, Product>

    @Query("DELETE FROM product")
    suspend fun deleteAllProduct()
}