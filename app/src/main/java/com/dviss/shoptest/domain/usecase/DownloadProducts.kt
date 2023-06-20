package com.dviss.shoptest.domain.usecase

import com.dviss.shoptest.domain.repository.ProductRepository

class DownloadProducts(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() = repository.downloadProducts()
}