package com.dviss.shoptest.domain.repository

import com.dviss.shoptest.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(): Flow<List<Product>>

    suspend fun downloadProducts()

}