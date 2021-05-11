package com.amrmustafa.adidas.models.states

import com.amrmustafa.adidas.models.ProductPresentation
import com.amrmustafa.adidas.models.ReviewPresentation

internal data class DetailsViewState(
    val isComplete: Boolean,
    val error: ErrorState?,
    val reviews: List<ReviewPresentation>?,
    val info: ProductPresentation?
)