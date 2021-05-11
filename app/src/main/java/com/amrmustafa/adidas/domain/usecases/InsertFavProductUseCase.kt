package com.amrmustafa.adidas.domain.usecases

import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.repository.IFavRepository
import kotlinx.coroutines.flow.Flow


class InsertFavProductUseCase(
    private val favRepository: IFavRepository
) : MainUseCase<Product, Flow<Long>> {

    override suspend fun invoke(params: Product) = favRepository.insertProduct(params)

}