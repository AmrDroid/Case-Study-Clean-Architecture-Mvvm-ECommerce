package com.amrmustafa.adidas.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductPresentation(
    val product_id: String,
    val name: String,
    val description: String,
    val imgUrl: String,
    val unit_price: String,
    val currency_symbol: String,
    val currency_short_form: String,
) : Parcelable