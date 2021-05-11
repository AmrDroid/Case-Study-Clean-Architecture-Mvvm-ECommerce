package com.amrmustafa.adidas.domain.usecases

import com.amrmustafa.adidas.domain.models.Review
import com.amrmustafa.adidas.domain.repository.IFavRepository
import kotlinx.coroutines.flow.Flow
import com.amrmustafa.adidas.domain.models.Result

class InsertFavReviewsUseCase(
    private val favRepository: IFavRepository
) : MainUseCase<List<Review>, Flow<Result>> {

    override suspend fun invoke(params: List<Review>) = favRepository.insertReviews(params)

}