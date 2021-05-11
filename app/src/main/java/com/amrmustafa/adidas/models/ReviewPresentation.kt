package com.amrmustafa.adidas.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewPresentation(
    val product_id: String,

    val title: String,

    val rating: String,

    val description: String,

    val added_date: String,

    val added_date_str: String,
) : Parcelable
