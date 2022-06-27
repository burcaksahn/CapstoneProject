package com.burcak.sahin.myapplication.ui.home.ui.home.data

import android.media.Rating
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "fav_table")
data class ProductModel (
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("price")
    @Expose
    var price: Double? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("category")
    @Expose
    var category: String? = null,

    @SerializedName("image")
    @Expose
    var image: String? = null,
    @SerializedName("rating")
    @Expose
    var rating: Rating? = null,
): Serializable