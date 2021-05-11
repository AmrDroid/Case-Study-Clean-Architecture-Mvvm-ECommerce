package com.amrmustafa.adidas.domain.usecases
import com.amrmustafa.adidas.domain.models.Review
import com.amrmustafa.adidas.domain.repository.IDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetReviewsUseCase(
    private val detailsRepository: IDetailsRepository
) : MainUseCase<String, Flow<List<Review>>> {

    override suspend operator fun invoke(params: String) =
        detailsRepository.getProductReviews(params)
}