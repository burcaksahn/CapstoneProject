package com.burcak.sahin.myapplication.ui.home.ui.detail.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.burcak.sahin.myapplication.ui.home.ui.home.data.ProductModel

@Dao
interface FavDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(fav: FavModel)

    @Delete
    fun delete(fav: FavModel)

    @Query("delete from fav_table")
    fun deleteAllGames()


    @Query("select * from fav_table order by id desc")
    fun getAllFav(): LiveData<List<FavModel>>

}