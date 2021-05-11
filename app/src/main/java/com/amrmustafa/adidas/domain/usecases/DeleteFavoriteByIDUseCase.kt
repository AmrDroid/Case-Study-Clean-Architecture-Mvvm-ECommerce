package com.amrmustafa.adidas.domain.usecases

import com.amrmustafa.adidas.domain.repository.IFavRepository
import kotlinx.coroutines.flow.Flow

class DeleteFavoriteByIDUseCase(
    private val favRepository: IFavRepository
) : MainUseCase<String, Flow<Int>> {

    override suspend fun invoke(params: String) = favRepository.deleteFavoriteByID(params)

}