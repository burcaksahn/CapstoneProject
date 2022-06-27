package com.burcak.sahin.myapplication.ui.home.ui.mybag

import com.burcak.sahin.myapplication.ui.home.ui.mybag.data.MyBagModel

interface MyBagClickListener {
    fun isPlus(myBagModel: MyBagModel)
    fun isMinus(myBagModel: MyBagModel)
}