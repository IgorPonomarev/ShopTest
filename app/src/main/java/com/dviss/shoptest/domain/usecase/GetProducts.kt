package com.dviss.shoptest.domain.usecase

import com.dviss.shoptest.domain.repository.ProductRepository

class GetProducts(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() = repository.getProducts()
}