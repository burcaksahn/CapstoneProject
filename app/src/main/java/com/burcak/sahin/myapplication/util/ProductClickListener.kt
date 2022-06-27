package com.burcak.sahin.myapplication.util

import com.burcak.sahin.myapplication.ui.home.ui.home.data.ProductModel

interface ProductClickListener {
    fun isProductCliked(productModel: ProductModel)
}