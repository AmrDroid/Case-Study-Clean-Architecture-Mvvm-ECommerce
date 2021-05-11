package com.amrmustafa.adidas.models.states


internal data class FavoriteDetailViewState(
    val isFavorite: Boolean,
    val error: ErrorState?
)