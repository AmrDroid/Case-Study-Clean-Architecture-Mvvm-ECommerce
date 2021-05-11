package com.amrmustafa.adidas.data.remote.api

import com.amrmustafa.adidas.data.remote.models.ProductResponse
import com.amrmustafa.adidas.data.remote.models.ReviewResponse
import com.amrmustafa.adidas.utils.Constants
import com.amrmustafa.adidas.utils.Constants.API_KEY
import retrofit2.http.*


interface ApiService {

    @GET("rest/products/all_collection_products")
    suspend fun searchProducts(
        @Query("api_key") api_key: String = API_KEY,
        @Query("id") id: String = "col1",
        @Query("limit") limit: String,
        @Query("offset") offset: String,
    ): List<ProductResponse>

    @GET("rest/rates/get")
    suspend fun getReviews(
        @Query("api_key") api_key: String = API_KEY,
        @Query("product_id") id: String,
    ): List<ReviewResponse>


    @FormUrlEncoded
    @POST("rest/rates/add_rating/api_key/{API_KEY}")
    suspend fun postReview(
        @Path("API_KEY") api_key: String = API_KEY,
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("rating") rating: String,
        @Field("user_id") userId: String,
        @Field("product_id") productId: String
    ): ReviewResponse


}