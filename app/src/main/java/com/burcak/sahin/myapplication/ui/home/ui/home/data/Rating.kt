package com.burcak.sahin.myapplication.ui.home.ui.home.data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating (
    @SerializedName("rate")
    @Expose
    var rate: Double? = null,

    @SerializedName("count")
    @Expose
    var count: Int? = null,
    ): Serializable