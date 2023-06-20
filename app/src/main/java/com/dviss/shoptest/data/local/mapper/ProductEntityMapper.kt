package com.dviss.shoptest.data.local.mapper

import com.dviss.shoptest.data.local.entity.ProductEntity
import com.dviss.shoptest.domain.model.Product

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        imageUrl = imageUrl
    )
}

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id ?: 0,
        title = title,
        price = price,
        description = description,
        category = category,
        imageUrl = imageUrl
    )
}