package com.ahmeturunveren.goshop.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmeturunveren.goshop.data.model.CartModel
import com.ahmeturunveren.goshop.data.model.FavoriteModel

@Database(entities = [CartModel::class,FavoriteModel::class], version = 4,exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun getProductFromDao():ProductDao
}