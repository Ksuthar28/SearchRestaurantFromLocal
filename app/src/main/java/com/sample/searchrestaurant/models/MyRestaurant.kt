package com.sample.searchrestaurant.models

data class MyRestaurant(
    val id: String,
    val image: String,
    val name: String,
    val cuisine: String,
    val location: String,
    var menuItems: String = "",
    val rating: Int,
)