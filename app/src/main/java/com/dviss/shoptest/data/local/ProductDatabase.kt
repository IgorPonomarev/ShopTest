package com.dviss.shoptest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dviss.shoptest.data.local.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class
    ],
    version = 1
)
abstract class ProductDatabase: RoomDatabase() {

    abstract val dao: ProductDao
}