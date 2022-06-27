package com.burcak.sahin.myapplication.ui.home.ui.dashboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.burcak.sahin.myapplication.ui.home.ui.home.data.ProductModel
import com.burcak.sahin.myapplication.util.Api
import com.burcak.sahin.myapplication.util.GetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {
    var endpoints: Api = Api()
    fun getCategories(): MutableLiveData<List<String>> {
        val apiResponse = MutableLiveData<List<String>>()
        val apiService = endpoints.getClient()!!.create(GetService::class.java)
        val call : Call<List<String>>? = apiService.getCategories()
        call?.enqueue(object : Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<List<String>>,
                response: Response<List<String>>
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!! as List<String>?)
                } else {
                    Log.d("TAG", "onResponse: Not Success")
                }
            }

        })
        return apiResponse
    }
}