package com.amrmustafa.adidas.models.states

import com.amrmustafa.adidas.models.ProductPresentation

internal data class SearchViewState(
    val isLoading: Boolean,
    val error: ErrorState?,
    val searchResults: List<ProductPresentation>?
)