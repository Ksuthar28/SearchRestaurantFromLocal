package com.sample.searchrestaurant.models

import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("menus")
    val menus: List<Menus>
)

data class Menus(
    @SerializedName("restaurantId")
    val restaurantId: Int,
    @SerializedName("categories")
    val categories: List<Category>
)

data class Category(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("menu-items")
    val menuItems: List<MenuItem>
)

data class MenuItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("images")
    val images: List<String>,
)