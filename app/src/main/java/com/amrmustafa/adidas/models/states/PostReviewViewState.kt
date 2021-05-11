package com.amrmustafa.adidas.models.states

import com.amrmustafa.adidas.models.ReviewPresentation

internal data class PostReviewViewState(
    val isComplete: Boolean,
    val error: ErrorState?,
    val reviews: ReviewPresentation?,
)