package com.burcak.sahin.myapplication.util

import com.burcak.sahin.myapplication.ui.home.ui.home.data.ProductModel
import retrofit2.http.GET
import retrofit2.http.Path

interface GetService {
    @GET("products")
    fun getProduct(): retrofit2.Call<List<ProductModel>>?
    @GET("products/{id}")
    fun getProductDetail(@Path("id")id: String): retrofit2.Call<ProductModel>?
    @GET("products/categories")
    fun getCategories(): retrofit2.Call<List<String>>?
    @GET("products/category/{name}")
    fun getProductListForCategory(@Path("name")id: String): retrofit2.Call<List<ProductModel>>?

}