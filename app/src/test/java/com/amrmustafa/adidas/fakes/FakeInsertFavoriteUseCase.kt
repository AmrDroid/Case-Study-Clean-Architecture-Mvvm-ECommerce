package com.amrmustafa.casestudy.fakes

import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.Result
import kotlinx.coroutines.flow.Flow

class FakeInsertFavoriteUseCase(
    result: Result
) : BaseTestUseCase<Long, Product>(result), MainUseCase<Product, Flow<Long>> {

    override suspend fun invoke(params: Product): Flow<Long> {
        Data.favorites.add(params)
        return execute(params)
    }

    override fun getValue(params: Product): Long {
        return if (Data.product.size == 1) 1 else 0
    }

}

