package com.amrmustafa.adidas.converters

import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.models.Review
import com.amrmustafa.adidas.models.ProductPresentation
import com.amrmustafa.adidas.models.ReviewPresentation


//From Presentation Layer To Domain

internal fun ProductPresentation.toDomain(): Product {
    return Product(
        product_id = product_id,
        name = name,
        description = description,
        imgUrl = imgUrl,
        unit_price = unit_price,
        currency_symbol = currency_symbol,
        currency_short_form = currency_short_form,
    )
}


internal fun List<ReviewPresentation>.toDomain(): List<Review> {
    val reviews = arrayListOf<Review>()
    for (review in this) {
        reviews.add(
            Review(
                product_id = review.product_id,
                title = review.title,
                description = review.description,
                rating = review.rating,
                added_date = review.added_date,
                added_date_str = review.added_date_str
            )
        )
    }
    return reviews
}

