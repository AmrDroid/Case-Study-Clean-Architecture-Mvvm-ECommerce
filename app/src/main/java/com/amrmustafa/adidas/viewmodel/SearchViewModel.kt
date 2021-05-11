package com.amrmustafa.adidas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amrmustafa.adidas.utils.ExceptionHandler
import com.amrmustafa.adidas.domain.usecases.MainUseCase
import com.amrmustafa.adidas.converters.toViewModel
import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.models.ProductPresentation
import com.amrmustafa.adidas.models.states.SearchViewState
import com.amrmustafa.adidas.models.states.ErrorState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

internal class SearchViewModel(
    private val searchProductsUseCase: MainUseCase<String, Flow<List<Product>>>
) : BaseViewModel() {


    private var searchJob: Job? = null

    val searchViewState: LiveData<SearchViewState>
        get() = _searchViewState

    private var _searchViewState = MutableLiveData<SearchViewState>()



    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        onSearchError(message)
    }


    init {
        _searchViewState.value =
            SearchViewState(
                isLoading = false,
                error = null,
                searchResults = null
            )
    }


    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }


    fun executeProductsSearch(productName: String) {
        searchJob?.cancel()
        searchJob = launchCoroutine {
            onSearchLoading()

            searchProductsUseCase(productName).collect { results ->
                val products = results.map { product -> product.toViewModel() }
                onSearchComplete(products)
            }
        }
    }


    private fun onSearchComplete(products: List<ProductPresentation>) {
        _searchViewState.value =
            _searchViewState.value?.copy(isLoading = false, searchResults = products)
    }

    private fun onSearchLoading() {
        _searchViewState.value = _searchViewState.value?.copy(isLoading = true)
    }

    private fun onSearchError(message: Int) {
        _searchViewState.value =
            _searchViewState.value?.copy(isLoading = false, error = ErrorState(message))
    }

}



