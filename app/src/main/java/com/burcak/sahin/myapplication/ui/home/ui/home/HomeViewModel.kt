package com.burcak.sahin.myapplication.ui.home.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.burcak.sahin.myapplication.ui.home.ui.home.data.ProductModel
import com.burcak.sahin.myapplication.util.Api
import com.burcak.sahin.myapplication.util.GetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    var endpoints : Api = Api()

    fun getProduct() : MutableLiveData<List<ProductModel>> {
        val apiResponse = MutableLiveData<List<ProductModel>>()
        val apiService = endpoints.getClient()!!.create(GetService::class.java)
        val call : Call<List<ProductModel>>? = apiService.getProduct()
        call?.enqueue(object : Callback<List<ProductModel>> {
            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<List<ProductModel>>,
                response: Response<List<ProductModel>>
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!! as List<ProductModel>?)
                } else {
                    Log.d("TAG", "onResponse: Not Success")
                }
            }

        })
        return apiResponse
    }
}