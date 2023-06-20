package com.dviss.shoptest.data.repository

import com.dviss.shoptest.data.local.ProductDao
import com.dviss.shoptest.data.local.mapper.toProduct
import com.dviss.shoptest.data.local.mapper.toProductEntity
import com.dviss.shoptest.domain.model.Product
import com.dviss.shoptest.domain.remote.ProductService
import com.dviss.shoptest.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val productDao: ProductDao,
    private val productService: ProductService
) : ProductRepository {
    override suspend fun getProducts(): Flow<List<Product>> {
        return productDao.getProducts().map { entities -> entities.map { it.toProduct() } }
    }

    override suspend fun downloadProducts() {
        productDao.saveProducts(productService.downloadProducts().map { it.toProductEntity() })
    }
}