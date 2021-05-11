package com.amrmustafa.casestudy.fakes

import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.Result
import kotlinx.coroutines.flow.Flow


class FakeSearchProductUseCase(
    result: Result
) : BaseTestUseCase<List<Product>, String>(result), MainUseCase<String, Flow<List<Product>>> {

    override suspend fun invoke(params: String): Flow<List<Product>> = execute(params)

    override fun getValue(params: String): List<Product> {
        return if (params.contentEquals("Amr")) Data.product else emptyList()
    }

}