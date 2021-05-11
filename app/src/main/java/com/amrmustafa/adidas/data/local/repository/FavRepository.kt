package com.amrmustafa.adidas.data.local.repository

import com.amrmustafa.adidas.data.local.dao.ProductDao
import com.amrmustafa.adidas.data.local.converters.toDomain
import com.amrmustafa.adidas.data.local.converters.toEntity
import com.amrmustafa.adidas.domain.models.Product
import com.amrmustafa.adidas.domain.models.Review
import com.amrmustafa.adidas.domain.repository.IFavRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.amrmustafa.adidas.domain.models.Result
//local Favorites Repository that interact with domain layer to provide data to presentation layer when required

class FavRepository(private val favoritesDao: ProductDao) : IFavRepository {

    override fun getAllFavorites(): Flow<List<Product>> = flow {
        val favs = favoritesDao.getAllFav()
        emit(favs.map { it.toDomain() })
    }

    override fun getAllReviews(): Flow<List<Review>> = flow {
        val reviews = favoritesDao.getAllReviews()
        emit(reviews.map { it.toDomain() })
    }

    override fun getFavoriteByID(id: String): Flow<Product?> = flow {
        val fav = favoritesDao.getFavByID(id)
        if (fav != null)
            emit(fav.toDomain())
        else
            emit(null)

    }

    override fun deleteFavoriteByID(id: String): Flow<Int> = flow {
        val rowsAffected = favoritesDao.deleteFavByID(id)
        emit(rowsAffected)
    }

    override fun insertReviews(reviews: List<Review>): Flow<Result> = flow {
         favoritesDao.insertReviews(reviews.toEntity())
    }

    override fun insertProduct(product: Product): Flow<Long> = flow {
        val result = favoritesDao.insertProduct(product.toEntity())
        emit(result)
    }

}