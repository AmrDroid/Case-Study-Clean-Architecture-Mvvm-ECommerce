package com.amrmustafa.casestudy.fakes

import com.amrmustafa.adidas.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.Result
import kotlinx.coroutines.flow.Flow


class FakeDeleteFavoriteByNameUseCase(
    result: Result
) : BaseTestUseCase<Int, String>(result), MainUseCase<String, Flow<Int>> {

    override suspend fun invoke(params: String): Flow<Int> = execute(params)

    override fun getValue(params: String): Int {
        return Data.favorites.find { it.name == params }?.run {
            listOf(Data.favorites.remove(this)).size
        } ?: 0
    }

}