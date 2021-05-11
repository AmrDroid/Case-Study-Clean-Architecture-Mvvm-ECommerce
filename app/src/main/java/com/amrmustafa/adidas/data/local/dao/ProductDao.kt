package com.amrmustafa.adidas.data.local.dao

import androidx.room.*
import com.amrmustafa.adidas.data.local.models.ProductEntity
import com.amrmustafa.adidas.data.local.models.ReviewEntity
import com.amrmustafa.adidas.domain.models.Result
//Local Database Operations
@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(reviews: ReviewEntity) : Long

    @Transaction
    suspend fun insertReviews(reviews: List<ReviewEntity>):Result {

        for (review in reviews) {
            insertReview(review)
        }
        return Result.SUCCESS
    }

    @Query("DELETE FROM products WHERE product_id=:productID")
    suspend fun deleteFavByID(productID: String): Int

    @Transaction
    @Query("SELECT * FROM products WHERE product_id=:productID")
    suspend fun getFavByID(productID: String): ProductEntity

    @Transaction
    @Query("SELECT * FROM products")
    suspend fun getAllFav(): List<ProductEntity>

    @Transaction
    @Query("SELECT * FROM reviews")
    suspend fun getAllReviews(): List<ReviewEntity>


}