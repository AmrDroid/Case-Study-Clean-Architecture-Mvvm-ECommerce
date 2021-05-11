package com.amrmustafa.casestudy.fakes

import com.amrmustafa.adidas.domain.models.Review
import com.amrmustafa.adidas.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.Result
import kotlinx.coroutines.flow.Flow

class FakeInsertReviewUseCase(
    result: Result
) : BaseTestUseCase<Long, List<Review>>(result), MainUseCase<List<Review>, Flow<Long>> {

    override suspend fun invoke(params: List<Review>): Flow<Long> {
        Data.reviews.addAll(params)
        return execute(params)
    }

    override fun getValue(params: List<Review>): Long {
        return if (Data.product.size == 1) 1 else 0
    }

}

