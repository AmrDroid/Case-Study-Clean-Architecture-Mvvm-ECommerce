package com.amrmustafa.adidas.data.remote.models

data class ProductResponse(
    val id: String,
    val name: String,
    val description: String,
    val unit_price: String,
    val imgUrl: String,
    val currency_symbol: String,
    val currency_short_form: String,
)