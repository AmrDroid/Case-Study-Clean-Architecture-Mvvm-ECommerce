package com.amrmustafa.casestudy.data.remote.helpers

import com.amrmustafa.casestudy.data.remote.resources.*
import com.amrmustafa.casestudy.data.remote.ApiConstants.EXISTING_Review_PARAMS
import com.amrmustafa.casestudy.data.remote.ApiConstants.NO_Products_URI
import com.amrmustafa.casestudy.data.remote.ApiConstants.NO_REVIEWS_URI
import com.amrmustafa.casestudy.data.remote.ApiConstants.Products_URI
import com.amrmustafa.casestudy.data.remote.ApiConstants.Reviews_URI
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection


internal class ApiRequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {

            Products_URI -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(PRODUCT_SEARCH_RESULT)
            }

            Reviews_URI + EXISTING_Review_PARAMS -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(REVIEWS_SEARCH_RESULT)
            }

            else -> throw IllegalArgumentException("Unknown Request Path ${request.path.toString()}")
        }
    }

}