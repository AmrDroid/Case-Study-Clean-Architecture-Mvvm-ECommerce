package com.amrmustafa.casestudy.data.local

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.amrmustafa.adidas.data.local.MainDatabase
import com.amrmustafa.adidas.data.local.dao.ProductDao
import com.amrmustafa.adidas.data.local.repository.FavRepository
import com.amrmustafa.adidas.domain.repository.IFavRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class FavRepositoryTest : AutoCloseKoinTest() {

    private lateinit var favRepository: IFavRepository
    private lateinit var db: MainDatabase
    protected lateinit var productDao: ProductDao

    @Before
    open fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MainDatabase::class.java).build()
        productDao = db.productsDao()
        favRepository = FavRepository(productDao)
    }

    @Test
    fun save_product_get_all_product_test() {
        runBlocking(Dispatchers.IO) {

            favRepository.insertProduct(Data.product).collect()

            favRepository.insertProduct(Data.product2).collect()

            val product = favRepository.getAllFavorites()

            product.collect { favs ->
                Truth.assertThat(favs).isEqualTo(listOf(Data.product,Data.product2))
            }
        }
    }

    @Test
    fun save_reviews_get_all_test() {
        runBlocking(Dispatchers.IO) {

            favRepository.insertReviews(arrayListOf(Data.review,Data.review2)).collect()

            val product = favRepository.getAllReviews()

            product.collect { favs ->
                Truth.assertThat(favs).isEqualTo(listOf(Data.review,Data.review2))
            }
        }
    }

    @Test
    fun get_specific_favorite_product_test() {
        runBlocking(Dispatchers.IO) {

            favRepository.insertProduct(Data.product2).collect()

            val favorite = favRepository.getFavoriteByID(Data.product2.product_id)

            favorite.collect { favs ->
                Truth.assertThat(favs).isEqualTo(Data.product2)
            }
        }
    }
    @Test
    fun delete_specific_favorite_product_Test() {
        runBlocking(Dispatchers.IO) {

            favRepository.insertProduct(Data.product).collect()

            val favorite = favRepository.deleteFavoriteByID(Data.product.product_id)

            favorite.collect { rowsAffected ->
                Truth.assertThat(rowsAffected).isEqualTo(1)
            }

        }
    }
    @After
    @Throws(IOException::class)
    fun deleteDataBase() {
        runBlocking(Dispatchers.IO) {
            db.clearAllTables()
        }
        db.close()
    }

}
