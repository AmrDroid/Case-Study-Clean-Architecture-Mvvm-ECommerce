package com.amrmustafa.adidas.converters

import com.amrmustafa.adidas.models.*
import com.amrmustafa.adidas.domain.models.*


//From Domain To Presentation Layer

internal fun Product.toViewModel(): ProductPresentation {
    return ProductPresentation(
        product_id = product_id,
        name = name,
        description = description,
        imgUrl = imgUrl,
        unit_price = unit_price,
        currency_symbol = currency_symbol,
        currency_short_form = currency_short_form,
    )
}


internal fun Review.toViewModel(): ReviewPresentation {
    return ReviewPresentation(
        product_id = product_id,
        title = title,
        description = description,
        rating = rating,
        added_date = added_date,
        added_date_str = added_date_str
    )
}

