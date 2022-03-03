package com.sample.searchrestaurant.utils

import android.content.Context
import com.sample.searchrestaurant.utils.MyExtension.menuNames
import com.sample.searchrestaurant.utils.MyExtension.resCity
import com.sample.searchrestaurant.models.Menu
import com.sample.searchrestaurant.models.MyRestaurant
import com.sample.searchrestaurant.models.Restaurant

object LocalRestaurant {

    /*
    Fetch local restaurants
     */
    private fun getRestaurant(context: Context) =
        context.getObjectFromJson<Restaurant>("restaurant.json")

    /*
    Fetch local menus
     */
    private fun getMenu(context: Context) =
        context.getObjectFromJson<Menu>("menu.json")

    /*
    Return consolidated results
     */
    fun getAllRestaurants(context: Context): MutableList<MyRestaurant> {
        val restaurant = getRestaurant(context)
        val menu = getMenu(context)
        val list = mutableListOf<MyRestaurant>()
        for (res in restaurant.restaurants) {
            list.add(
                MyRestaurant(
                    id = res.id,
                    image = res.photograph,
                    name = res.name,
                    cuisine = res.cuisine_type,
                    location = res.address.resCity(),
                    rating = (res.reviews.sumOf { it.rating }) / (res.reviews.size)
                )
            )
        }
        for ((index, value) in list.withIndex()) {
            value.menuItems = menu.menus[index].categories.menuNames()
        }
        return list;
    }
}