package com.amrmustafa.casestudy.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amrmustafa.adidas.viewmodel.SearchViewModel
import com.amrmustafa.casestudy.fakes.FakeSearchProductUseCase
import com.amrmustafa.casestudy.utils.Data
import com.amrmustafa.casestudy.utils.Result
import com.amrmustafa.casestudy.utils.observeOnce
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
@ExperimentalCoroutinesApi
class SearchViewModelTest : BaseViewModelTest() {

    private lateinit var dashboardSearchViewModel: SearchViewModel


    override fun prepareViewModel(result: Result) {
        val searchCharactersUseCase = FakeSearchProductUseCase(result)
        dashboardSearchViewModel = SearchViewModel(searchCharactersUseCase)
    }


    @Test
    fun search_result_success_state() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(Result.SUCCESS)
            dashboardSearchViewModel.executeProductsSearch(Data.SEARCH_PARAM)

            advanceTimeBy(500)

            dashboardSearchViewModel.searchViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNull()
                Truth.assertThat(state.searchResults).isNotEmpty()
            }
        }
    }
    @Test
    fun invalid_search_query_error_state() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(Result.FAILURE)
            dashboardSearchViewModel.executeProductsSearch("eed")

            advanceTimeBy(600)

            dashboardSearchViewModel.searchViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNotNull()
            }

        }
    }
    @Test
    fun empty_search_query_error_state() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(Result.FAILURE)
            dashboardSearchViewModel.executeProductsSearch("")

            advanceTimeBy(600)

            dashboardSearchViewModel.searchViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNotNull()
            }

        }
    }


}