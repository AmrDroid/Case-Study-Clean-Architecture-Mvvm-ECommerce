package com.amrmustafa.adidas.domain.usecases
import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.repository.IFavRepository
import kotlinx.coroutines.flow.Flow


class GetProductByIDUseCase(
    private val favRepository: IFavRepository
) : MainUseCase<String, Flow<Product?>> {

    override suspend fun invoke(params: String) = favRepository.getFavoriteByID(params)

}