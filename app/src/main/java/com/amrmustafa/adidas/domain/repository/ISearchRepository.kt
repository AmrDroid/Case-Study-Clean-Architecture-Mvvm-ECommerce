package com.amrmustafa.adidas.domain.repository

import com.amrmustafa.adidas.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    suspend fun searchProducts(
        productName: String,
        limit: String="50",
    ): Flow<List<Product>>
}