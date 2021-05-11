package com.amrmustafa.casestudy.data.remote.repository

import com.amrmustafa.adidas.data.remote.repository.SearchRepository
import com.amrmustafa.adidas.domain.repository.ISearchRepository
import com.amrmustafa.casestudy.data.remote.BaseClient
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class SearchRepositoryClient : BaseClient() {

    private lateinit var reviewSearchRepository: ISearchRepository

    @Before
    override fun setup() {
        super.setup()
        reviewSearchRepository = SearchRepository(apiService)
    }

    @Test
    fun search_review_existing_result_test() {
        runBlocking {
            val results = reviewSearchRepository.searchProducts("")
            results.collect {
                Truth.assertThat(it).isNotEmpty()
            }
        }
    }

}