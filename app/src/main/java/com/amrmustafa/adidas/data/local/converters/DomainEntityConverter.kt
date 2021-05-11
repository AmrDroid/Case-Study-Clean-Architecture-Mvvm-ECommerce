package com.amrmustafa.adidas.data.local.converters

import com.amrmustafa.adidas.data.local.models.ProductEntity
import com.amrmustafa.adidas.data.local.models.ReviewEntity
import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.models.Review


//From Domain Layer To Data Layer

internal fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        productID = product_id,
        name = name,
        description = description,
        imgUrl = imgUrl,
        unitPrice = unit_price,
        currencySymbol = currency_symbol,
        currencyShortForm = currency_short_form,
    )

}

internal fun ProductEntity.toDomain(): Product = Product(
    product_id = productID,
    name = name,
    description = description,
    imgUrl = imgUrl,
    unit_price = unitPrice,
    currency_symbol = currencySymbol,
    currency_short_form = currencyShortForm,
)


internal fun List<Review>.toEntity(): List<ReviewEntity> {
    var reviews: ArrayList<ReviewEntity> = arrayListOf<ReviewEntity>() as ArrayList<ReviewEntity>
    for (review in this) {
        reviews.add(
            ReviewEntity(
                productID = review.product_id,
                title = review.title,
                description = review.description,
                rating = review.rating,
                addedDate = review.added_date,
                addedDateStr = review.added_date_str
            )
        )
    }
    return reviews
}

internal fun ReviewEntity.toDomain(): Review = Review(
    product_id = productID,
    title = title,
    rating = rating,
    description = description,
    added_date = addedDate,
    added_date_str = addedDateStr
)


internal fun Review.toEntity(): ReviewEntity = ReviewEntity(
    productID = product_id,
    title = title,
    rating = rating,
    description = description,
    addedDate=added_date ,
    addedDateStr=added_date_str
)
