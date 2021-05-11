package com.amrmustafa.adidas.domain.repository


import androidx.room.Transaction
import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.models.Review
import kotlinx.coroutines.flow.Flow
import com.amrmustafa.adidas.domain.models.Result
interface IFavRepository {

    fun getAllFavorites(): Flow<List<Product>>

    fun getAllReviews(): Flow<List<Review>>

    fun getFavoriteByID(name: String): Flow<Product?>

    fun deleteFavoriteByID(name: String): Flow<Int>

    fun insertProduct(product: Product): Flow<Long>

    fun insertReviews(reviews: List<Review>): Flow<Result>


}