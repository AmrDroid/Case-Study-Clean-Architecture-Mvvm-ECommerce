package com.amrmustafa.casestudy.fakes

import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.Result
import kotlinx.coroutines.flow.Flow


class FakeGetFavoriteByNameUseCase(
    result: Result
) : BaseTestUseCase<Product?, String>(result), MainUseCase<String, Flow<Product?>> {

    override suspend fun invoke(params: String) = execute(params)

    override fun getValue(params: String): Product? {
        return Data.favorites.filter { it.name == params }.firstOrNull()
    }


}