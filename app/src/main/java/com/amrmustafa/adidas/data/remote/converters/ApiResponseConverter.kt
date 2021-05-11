package com.amrmustafa.adidas.data.remote.converters

import com.amrmustafa.adidas.data.remote.models.ProductResponse
import com.amrmustafa.adidas.data.remote.models.ReviewResponse
import com.amrmustafa.adidas.domain.models.*

//from remote data layer to provide data to domain layer

internal fun ProductResponse.toDomain(): Product {
    return Product(
        product_id = id,
        name = name,
        description = description,
        imgUrl = imgUrl,
        unit_price = unit_price,
        currency_symbol = currency_symbol,
        currency_short_form = currency_short_form
    )
}

internal fun ReviewResponse.toDomain(): Review {
    return Review(
        product_id = id,
        title = title,
        rating = rating,
        description = description,
        added_date = added_date,
        added_date_str = added_date_str
    )
}