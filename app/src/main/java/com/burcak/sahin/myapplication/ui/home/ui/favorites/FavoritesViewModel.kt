package com.burcak.sahin.myapplication.ui.home.ui.favorites

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.burcak.sahin.myapplication.ui.home.ui.detail.data.db.FavDataBase
import com.burcak.sahin.myapplication.ui.home.ui.detail.data.db.FavModel
import com.burcak.sahin.myapplication.util.subscribeOnBackground

class FavoritesViewModel : ViewModel() {
    private var database: FavDataBase? = null
    private val _fav = MutableLiveData<LiveData<List<FavModel>>>()
    val fav: LiveData<List<FavModel>>? = _fav.value
    fun getFavorites() {
        return subscribeOnBackground {
            database?.favDao()?.getAllFav()
        }
    }
    fun setContext(context: Context) {

    }
}