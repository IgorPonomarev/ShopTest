package com.dviss.shoptest.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    @SerialName("image") val imageUrl: String,
    val rating: ProductRatingDto
)
