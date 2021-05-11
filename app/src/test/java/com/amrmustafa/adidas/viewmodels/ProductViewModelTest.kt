package com.amrmustafa.casestudy.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amrmustafa.adidas.viewmodel.FavoritesViewModel
import com.google.common.truth.Truth
import com.amrmustafa.casestudy.fakes.FakeGetAllFavoritesUseCase
import com.amrmustafa.casestudy.utils.Result
import com.amrmustafa.casestudy.utils.observeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
@ExperimentalCoroutinesApi
internal class ProductViewModelTest : BaseViewModelTest() {

    private lateinit var dashboardFavoritesViewModel: FavoritesViewModel

    override fun prepareViewModel(result: Result) {
        val getAllFavoritesUseCase = FakeGetAllFavoritesUseCase(result)

        dashboardFavoritesViewModel = FavoritesViewModel(
            getAllFavoritesUseCase
        )

    }


    @Test
    fun get_all_favorites() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(Result.SUCCESS)

            dashboardFavoritesViewModel.getAllFavorites()

            dashboardFavoritesViewModel.favoritesViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNull()
                Truth.assertThat(state.isLoading).isFalse()
                Truth.assertThat(state.favorites).isNotNull()
            }
        }
    }


}