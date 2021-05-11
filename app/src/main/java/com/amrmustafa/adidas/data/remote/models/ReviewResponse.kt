package com.amrmustafa.adidas.data.remote.models

data class ReviewResponse(
    val id: String,
    val user_id: String,
    val rating: String,
    val title: String,
    val description: String,
    val added_date: String,
    val added_date_str: String,
    )

