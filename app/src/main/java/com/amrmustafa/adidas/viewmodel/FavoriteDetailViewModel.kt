package com.amrmustafa.adidas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amrmustafa.adidas.converters.toDomain
import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.utils.ExceptionHandler
import com.amrmustafa.adidas.domain.models.Review
import com.amrmustafa.adidas.domain.usecases.MainUseCase
import com.amrmustafa.adidas.models.ProductPresentation
import com.amrmustafa.adidas.models.ReviewPresentation
import com.amrmustafa.adidas.models.states.ErrorState
import com.amrmustafa.adidas.models.states.FavoriteDetailViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import com.amrmustafa.adidas.domain.models.Result

internal class FavoriteDetailViewModel(
    private val deleteFavoriteByIDUseCase: MainUseCase<String, Flow<Int>>,

    private val insertProductUseCase: MainUseCase<Product, Flow<Long>>,

    private val insertReviewsUseCase: MainUseCase<List<Review>, Flow<Result>>,

    private val getProductByIDUseCase: MainUseCase<String, Flow<Product?>>
) : BaseViewModel() {

    private var saveFavoriteJob: Job? = null

    private var deleteFavoriteJob: Job? = null

    private var getFavoriteJob: Job? = null

    val favoriteViewState: LiveData<FavoriteDetailViewState>
        get() = _favoriteViewState

    private var _favoriteViewState = MutableLiveData<FavoriteDetailViewState>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _favoriteViewState.value = _favoriteViewState.value?.copy(error = ErrorState(message))
    }


    init {
        _favoriteViewState.value = FavoriteDetailViewState(isFavorite = false, error = null)
    }

    override fun onCleared() {
        super.onCleared()
        saveFavoriteJob?.cancel()
        getFavoriteJob?.cancel()
        deleteFavoriteJob?.cancel()
    }

    fun saveProduct(product: ProductPresentation) {
        saveFavoriteJob = launchCoroutine {
            insertProductUseCase(product.toDomain()).collect { result ->
                if (result != null) {
                    _favoriteViewState.value =
                        _favoriteViewState.value?.copy(isFavorite = true, error = null)
                    Log.i(this.javaClass.simpleName, "Saving Product Success!!")

                } else {
                    Log.i(this.javaClass.simpleName, "Saving Product ErrorState!!")
                }
            }
        }
    }

    fun saveReviews(reviews: List<ReviewPresentation>) {
        saveFavoriteJob = launchCoroutine {
            insertReviewsUseCase(reviews.toDomain()).collect { result ->
                if (result == Result.SUCCESS) {
                    _favoriteViewState.value =
                        _favoriteViewState.value?.copy(isFavorite = true, error = null)
                } else {
                    Log.i(this.javaClass.simpleName, "Saving Product ErrorState2!!")
                }
            }
        }
    }


    fun deleteFavorite(name: String) {
        deleteFavoriteJob = launchCoroutine {
            deleteFavoriteByIDUseCase(name).collect { row ->
                if (row == 1) {
                    _favoriteViewState.value = _favoriteViewState.value?.copy(isFavorite = false)
                }
            }
        }
    }

    fun getFavorite(id: String) {
        getFavoriteJob = launchCoroutine {
            getProductByIDUseCase(id).collect { fav ->
                fav?.run {
                    _favoriteViewState.value = _favoriteViewState.value?.copy(isFavorite = true)
                }
            }
        }
    }
}
