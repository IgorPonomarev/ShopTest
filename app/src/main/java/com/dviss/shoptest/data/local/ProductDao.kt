package com.dviss.shoptest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dviss.shoptest.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductEntity>>

}