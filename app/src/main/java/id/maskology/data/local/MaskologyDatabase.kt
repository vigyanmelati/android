package id.maskology.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.maskology.data.model.Category
import id.maskology.data.model.Product
import id.maskology.data.model.Store

@Database(
    entities = [
        Category::class,
        Store::class,
        Product::class,
        StoreRemoteKeys::class,
        ProductRemoteKeys::class,
        CategoryRemoteKeys::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MaskologyDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun storeDao(): StoreDao
    abstract fun productDao(): ProductDao
    abstract fun productRemoteKeysDao(): ProductRemoteKeysDao
    abstract fun storeRemoteKeysDao(): StoreRemoteKeysDao
    abstract fun categoryRemoteKeysDao(): CategoryRemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: MaskologyDatabase? = null

        @JvmStatic
        fun getInstance(application: Application): MaskologyDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    application,
                    MaskologyDatabase::class.java, "maskologyDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}