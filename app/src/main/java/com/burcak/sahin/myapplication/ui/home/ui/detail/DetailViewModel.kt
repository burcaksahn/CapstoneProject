package com.burcak.sahin.myapplication.ui.home.ui.detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burcak.sahin.myapplication.ui.home.ui.detail.data.db.FavDataBase
import com.burcak.sahin.myapplication.ui.home.ui.detail.data.db.FavModel
import com.burcak.sahin.myapplication.ui.home.ui.home.data.ProductModel
import com.burcak.sahin.myapplication.ui.home.ui.mybag.MyBagFragment
import com.burcak.sahin.myapplication.ui.home.ui.mybag.data.MyBagModel
import com.burcak.sahin.myapplication.util.Api
import com.burcak.sahin.myapplication.util.GetService
import com.burcak.sahin.myapplication.util.subscribeOnBackground
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    var detail: ProductModel? = null
    var endpoints : Api = Api()
    private var database: FavDataBase? = null
    private val _finishState = MutableLiveData<Boolean>()
    val finishState: LiveData<Boolean> = _finishState
    fun onClick() {
        _finishState.value = true
    }
    fun setContext(context: Context) {
        database = FavDataBase.getInstance(context)
    }
    fun finishStateRemove() {
        _finishState.value = false
    }
    fun insert() {
        if(detail != null) {
            val favModel = FavModel(id = detail!!.id!!, category = detail!!.category!!, path = detail!!.image!!, price = detail!!.price!!.toString())
            subscribeOnBackground {
                database?.favDao()?.insert(favModel)
            }
        }

    }
    fun insertBag() {
        val model = MyBagModel(detail!!.id.toString(), detail!!.title.toString(), count = 1 , path = detail!!.image.toString(), price = detail!!.price!!.toString())
        MyBagFragment.addToList(model)
    }
    fun getData(id: String): MutableLiveData<ProductModel> {
        val apiResponse = MutableLiveData<ProductModel>()
        val apiService = endpoints.getClient()!!.create(GetService::class.java)
        val call : Call<ProductModel>? = apiService.getProductDetail(id)
        call?.enqueue(object : Callback<ProductModel> {
            override fun onFailure(call: Call<ProductModel>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<ProductModel>,
                response: Response<ProductModel>
            ) {
                if (response.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    Log.d("TAG", "onResponse: Not Success")
                }
            }

        })
        return apiResponse
    }
}