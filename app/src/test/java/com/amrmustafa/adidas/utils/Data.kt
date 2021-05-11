package com.amrmustafa.casestudy.utils

import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.models.Review
import com.amrmustafa.adidas.models.ProductPresentation
import com.amrmustafa.adidas.models.ReviewPresentation


object Data {
    val product = arrayListOf (
        Product(
        "123",
        "12 BBY",
        "123",
        "https://google.com",
        "1000",
        "$",
        "USD"
    ), Product(
        "1234",
        "12 BBYY",
        "123",
        "https://google.com",
        "100",
        "$",
        "USD"
    )
    )
    val reviews = arrayListOf(
        Review(
            "rat4418d1743b2f09c46bc7934a5c9a5bfb",
            "hii",
            "2",
            "hii",
            "2021-05-10 15:08:54",
            "17 hours ago"
        ),
        Review(
            "ratd5ba3cc05f6e6fc73f46490a3cfcc6d4",
            "hii",
            "2",
            "hii",
            "2021-05-10 15:08:54",
            "17 hours ago"
        ),
        Review(
            "ratc94c6ab9def111b8c6fe5aaec728e0e0",
            "hii",
            "2",
            "hii",
            "2021-05-10 15:08:54",
            "17 hours ago"
        )
    )
    val favorites = mutableListOf<Product>()

    const val CHARACTER_URL = "/api/people/1/"
    const val SEARCH_PARAM = "Amr"

}
