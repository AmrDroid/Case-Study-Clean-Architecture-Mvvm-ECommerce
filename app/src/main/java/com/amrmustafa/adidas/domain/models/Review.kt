package com.amrmustafa.adidas.domain.models

data class Review(
    val product_id: String,

    val title: String,

    val rating: String,

    val description: String,

    val added_date: String,

    val added_date_str: String,
)

