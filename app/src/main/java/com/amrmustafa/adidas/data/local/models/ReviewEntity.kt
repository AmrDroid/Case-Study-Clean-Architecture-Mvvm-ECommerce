package com.amrmustafa.adidas.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewEntity(


    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val productID: String,

    val title: String,

    val description: String,

    val rating: String,

    @ColumnInfo(name = "added_date")
    val addedDate: String,

    @ColumnInfo(name = "added_date_str")
    val addedDateStr: String,
)