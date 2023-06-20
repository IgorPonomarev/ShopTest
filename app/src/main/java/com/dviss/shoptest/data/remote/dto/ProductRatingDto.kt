package com.dviss.shoptest.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductRatingDto(
    val rate: Double,
    val count: Int
)
