package com.amrmustafa.casestudy.data.remote

internal object ApiConstants {
    const val Products_URI = "/rest/products/all_collection_products?api_key=team&id=col1&limit=50&offset="

    const val NO_Products_URI = "/rest/products/all_collection_products?api_key=team1&id=col1&limit=50&offset="

    const val NO_REVIEWS_URI = "/rest/rates/get?api_key=team&product_id=prd050276af974ebb912e9f6c56847e747"

    const val Reviews_URI = "/rest/rates/get?api_key=team&product_id="

    const val EXISTING_Review_PARAMS = "prd050276af974ebb912e9f6c56847e7477"
}