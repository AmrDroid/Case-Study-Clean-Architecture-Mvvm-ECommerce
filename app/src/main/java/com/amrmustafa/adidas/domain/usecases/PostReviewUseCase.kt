package com.amrmustafa.adidas.domain.usecases

import com.amrmustafa.adidas.domain.models.Review
import com.amrmustafa.adidas.domain.repository.IDetailsRepository
import kotlinx.coroutines.flow.Flow

class PostReviewUseCase(
    private val detailsRepository: IDetailsRepository
) : MainUseCase<Review, Flow<Review>> {

    override suspend operator fun invoke(params: Review) =
        detailsRepository.postReview(params)
}