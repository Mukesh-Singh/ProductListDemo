package com.mukesh.products.demo.data.repository.datasource.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mukesh.products.demo.data.models.FavoriteEntity

/**
Created by Mukesh
 **/

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    fun getAllFavoriteIds(): List<FavoriteEntity>

    @Insert(onConflict = REPLACE)
    fun insert(favoriteEntity: FavoriteEntity)

    @Insert
    fun insertAll(favoriteEntities: List<FavoriteEntity>)

    @Delete
    fun delete(favoriteEntity: FavoriteEntity)

    @Query("Select count() from favorites where product_id =:productId")
    fun getCount(productId: String): Int
}