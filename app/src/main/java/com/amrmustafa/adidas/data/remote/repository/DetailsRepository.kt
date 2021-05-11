package com.amrmustafa.adidas.data.remote.repository

import com.amrmustafa.adidas.data.remote.api.ApiService
import com.amrmustafa.adidas.data.remote.converters.toDomain
import com.amrmustafa.adidas.domain.models.*
import com.amrmustafa.adidas.domain.repository.IDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


//Details Repository that interact with domain layer to provide data to presentation layer when required

class DetailsRepository(
    private val apiService: ApiService
) : IDetailsRepository {


    override suspend fun getProductReviews(productID: String): Flow<List<Review>> = flow {
        val reviewsResponse = apiService.getReviews(id=productID)

        val reviews = mutableListOf<Review>()

        for (review in reviewsResponse) {
            reviews.add(review.toDomain())
        }
        emit(reviews)
    }

    override suspend fun postReview(review: Review): Flow<Review> = flow {

        val reviewsResponse = apiService.postReview(
            title = review.title,
            description = review.description,
            rating = review.rating,
            userId = review.product_id,
            productId = review.product_id
        )
        emit(reviewsResponse.toDomain())
    }
}