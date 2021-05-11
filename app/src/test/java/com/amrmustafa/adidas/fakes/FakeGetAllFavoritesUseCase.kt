package com.amrmustafa.casestudy.fakes

import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.Result
import kotlinx.coroutines.flow.Flow


class FakeGetAllFavoritesUseCase(
    result: Result
) : BaseTestUseCase<List<Product>, Unit>(result), MainUseCase<Unit, Flow<List<Product>>> {

    override suspend fun invoke(params: Unit): Flow<List<Product>> = execute(params)

    override fun getValue(params: Unit): List<Product> = Data.favorites

}