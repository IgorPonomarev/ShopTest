package com.dviss.shoptest.ui

import com.dviss.shoptest.domain.model.Product

data class AppState(
    val products: List<Product> = emptyList(),
    val location: String = "Москва"
)
