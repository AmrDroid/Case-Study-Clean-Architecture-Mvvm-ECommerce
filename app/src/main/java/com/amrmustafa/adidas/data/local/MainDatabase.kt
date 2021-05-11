package com.amrmustafa.adidas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amrmustafa.adidas.data.local.dao.ProductDao
import com.amrmustafa.adidas.data.local.models.ProductEntity
import com.amrmustafa.adidas.data.local.models.ReviewEntity


@Database(entities = [ProductEntity::class, ReviewEntity::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductDao
}