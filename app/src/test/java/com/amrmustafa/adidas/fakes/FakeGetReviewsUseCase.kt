package com.amrmustafa.casestudy.fakes

import com.amrmustafa.adidas.domain.models.Review
import com.amrmustafa.adidas.domain.usecases.MainUseCase
import com.amrmustafa.casestudy.data.fakes.BaseTestUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.Data.CHARACTER_URL
import com.amrmustafa.casestudy.utils.Result
import kotlinx.coroutines.flow.Flow



class FakeGetReviewsUseCase(
    result: Result
) : BaseTestUseCase<ArrayList<Review>, String>(result), MainUseCase<String, Flow<ArrayList<Review>>> {

    override suspend fun invoke(params: String): Flow<ArrayList<Review>> = execute(params)

    override fun getValue(params: String): ArrayList<Review> {
        return if (params.contentEquals(CHARACTER_URL)) Data.reviews else arrayListOf()
    }

}