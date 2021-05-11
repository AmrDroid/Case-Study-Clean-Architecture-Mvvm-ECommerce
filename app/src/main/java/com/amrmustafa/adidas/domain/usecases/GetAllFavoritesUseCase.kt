package com.amrmustafa.adidas.domain.usecases

import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.repository.IFavRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoritesUseCase(
    private val favRepository: IFavRepository
) : MainUseCase<Unit, Flow<List<Product>>> {

    override suspend fun invoke(params: Unit) = favRepository.getAllFavorites()

}