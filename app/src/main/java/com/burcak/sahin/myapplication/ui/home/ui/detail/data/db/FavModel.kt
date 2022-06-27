package com.burcak.sahin.myapplication.ui.home.ui.detail.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class FavModel(
    @PrimaryKey
    val id: Int,
    val category: String,
    val path: String,
    val price: String
)