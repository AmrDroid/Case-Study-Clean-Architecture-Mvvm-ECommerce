package com.amrmustafa.adidas.domain.usecases

import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCase(
    private val searchRepository: ISearchRepository
) : MainUseCase<String, Flow<List<Product>>> {

    override suspend operator fun invoke(productName: String) = searchRepository.searchProducts(productName)

}