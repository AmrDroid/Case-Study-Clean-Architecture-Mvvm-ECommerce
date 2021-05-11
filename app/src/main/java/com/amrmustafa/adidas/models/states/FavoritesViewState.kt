package com.amrmustafa.adidas.models.states

import com.amrmustafa.adidas.models.ProductPresentation

internal data class FavoritesViewState(
    val isLoading: Boolean,
    val error: ErrorState?,
    val favorites: List<ProductPresentation>?
)