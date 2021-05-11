package com.amrmustafa.casestudy.data.remote.repository

import com.amrmustafa.adidas.data.remote.repository.DetailsRepository
import com.amrmustafa.adidas.domain.repository.IDetailsRepository
import com.amrmustafa.casestudy.data.remote.ApiConstants.EXISTING_Review_PARAMS
import com.amrmustafa.casestudy.data.remote.BaseClient
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class DetailsRepositoryClient : BaseClient() {

    private lateinit var detailsRepository: IDetailsRepository

    @Before
    override fun setup() {
        super.setup()
        detailsRepository = DetailsRepository(apiService)
    }

    @Test
    fun get_reviews_test() {
        runBlocking {
            val reviews = detailsRepository.getProductReviews(EXISTING_Review_PARAMS)

            reviews.collect { reviews ->
                Truth.assertThat(reviews.size).isAtLeast(1)
            }

        }
    }
}
