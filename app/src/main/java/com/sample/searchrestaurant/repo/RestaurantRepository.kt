package com.sample.searchrestaurant.repo

import android.content.Context
import com.sample.searchrestaurant.R
import com.sample.searchrestaurant.models.MyRestaurant
import com.sample.searchrestaurant.utils.LocalRestaurant
import com.sample.searchrestaurant.utils.Resource
import javax.inject.Inject

class RestaurantRepository @Inject constructor() {

    /*
  Fetch all matching restaurants
   */
    fun getAllRestaurants(
        query: String,
        context: Context
    ): Resource<MutableList<MyRestaurant>> {
        val search = query.lowercase().trimStart().trimEnd()
        var list = mutableListOf<MyRestaurant>()
        if (search.isNotEmpty()) list = LocalRestaurant.getAllRestaurants(context).filter {
            it.name.lowercase().contains(search) || it.cuisine.lowercase()
                .contains(search) || it.menuItems.lowercase().contains(search)
        }.toMutableList()
        return if (search.isNotEmpty() && list.isNotEmpty()) Resource.Success(list) else Resource.Error(
            context.getString(R.string.no_record_found)
        )
    }
}