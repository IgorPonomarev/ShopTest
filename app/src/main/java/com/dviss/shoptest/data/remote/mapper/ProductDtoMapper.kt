package com.dviss.shoptest.data.remote.mapper

import com.dviss.shoptest.data.remote.dto.ProductDto
import com.dviss.shoptest.domain.model.Product

fun ProductDto.toProduct() = Product(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    imageUrl = imageUrl
)