package com.amrmustafa.casestudy.data.local

import com.amrmustafa.adidas.data.local.models.ProductEntity
import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.models.Review


internal object Data {
    val product = Product(
        "123",
        "product 1",
        "product 1",
        "https://google.com",
        "125",
        "$",
        "USD",
        )
    val product2 = Product(
        "1234",
        "product 2",
        "product 2",
        "https://google.com",
        "125",
        "$",
        "USD",
    )

    val review= Review(
        "123",
        "good",
        "4",
        "good",
        "11/05/2021",
        "11/05/2021"
    )

    val review2= Review(
        "1234",
        "good",
        "3",
        "good",
        "11/05/2021",
        "11/05/2021"
    )
}