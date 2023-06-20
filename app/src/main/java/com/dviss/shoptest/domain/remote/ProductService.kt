package com.dviss.shoptest.domain.remote

import com.dviss.shoptest.domain.model.Product

interface ProductService {
    suspend fun downloadProducts(): List<Product>
}