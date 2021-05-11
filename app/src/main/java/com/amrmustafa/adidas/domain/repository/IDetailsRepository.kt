package com.amrmustafa.adidas.domain.repository


import com.amrmustafa.adidas.domain.models.Review
import kotlinx.coroutines.flow.Flow


interface IDetailsRepository {

    suspend fun getProductReviews(priductID: String): Flow<List<Review>>
    suspend fun postReview(review: Review): Flow<Review>

}