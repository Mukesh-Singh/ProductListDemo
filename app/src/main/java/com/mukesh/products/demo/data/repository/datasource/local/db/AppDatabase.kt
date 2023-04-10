package com.mukesh.products.demo.data.repository.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mukesh.products.demo.data.models.FavoriteEntity
import com.mukesh.products.demo.data.repository.datasource.local.db.AppDatabase.Companion.DATABASE_VERSION

/**
Created by Mukesh
 **/

@Database(entities = [FavoriteEntity::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    companion object{
        const val DATABASE_NAME = "product_database"
        const val DATABASE_VERSION = 1
    }

    abstract fun favoriteDao(): FavoriteDao
}