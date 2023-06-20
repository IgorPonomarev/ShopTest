package com.dviss.shoptest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val imageUrl: String
)
