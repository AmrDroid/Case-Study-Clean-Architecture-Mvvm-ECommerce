package com.amrmustafa.adidas.domain.models

data class Product(
    val product_id: String,
    val name: String,
    val description: String,
    val imgUrl: String,
    val unit_price: String,
    val currency_symbol: String,
    val currency_short_form: String,
)
