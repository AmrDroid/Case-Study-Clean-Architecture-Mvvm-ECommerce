package com.amrmustafa.adidas.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(

    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val productID: String,

    val name: String,

    val description: String,

    val imgUrl: String,

    @ColumnInfo(name = "unit_price")
    val unitPrice: String,

    @ColumnInfo(name = "currency_symbol")
    val currencySymbol: String,

    @ColumnInfo(name = "currency_short_form")
    val currencyShortForm: String,

    )