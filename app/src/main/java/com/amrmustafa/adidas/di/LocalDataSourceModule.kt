package com.amrmustafa.adidas.di

import androidx.room.Room
import com.amrmustafa.adidas.data.local.MainDatabase
import com.amrmustafa.adidas.data.local.dao.ProductDao
import com.amrmustafa.adidas.data.local.repository.FavRepository
import com.amrmustafa.adidas.domain.repository.IFavRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module



val localDataSourceModule = module {

    single<IFavRepository> { FavRepository(favoritesDao = get()) }

    single {
        Room.databaseBuilder(androidContext(), MainDatabase::class.java, "db")
            .build()
    }

    single { provideFavoritesDao(db = get()) }


}

internal fun provideFavoritesDao(db: MainDatabase): ProductDao = db.productsDao()