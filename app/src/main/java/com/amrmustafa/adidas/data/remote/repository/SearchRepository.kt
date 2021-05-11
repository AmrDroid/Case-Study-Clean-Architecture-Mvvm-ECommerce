package com.amrmustafa.adidas.data.remote.repository

import com.amrmustafa.adidas.data.remote.api.ApiService
import com.amrmustafa.adidas.data.remote.converters.toDomain
import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//Search Repository that interact with domain layer to provide data to presentation layer when required


class SearchRepository(
    private val apiService: ApiService
) : ISearchRepository {

    override suspend fun searchProducts(
        productName: String,
        limit: String,
    ): Flow<List<Product>> = flow {
        val searchResponse = apiService.searchProducts(offset= productName,limit = limit)


        val products = mutableListOf<Product>()
        for (product in searchResponse) {
            products.add(product.toDomain())
        }
        emit(products)
    }

}