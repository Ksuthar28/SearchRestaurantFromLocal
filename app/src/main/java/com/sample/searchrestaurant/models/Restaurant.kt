package com.sample.searchrestaurant.models

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("restaurants")
    val restaurants: List<Restaurants>
)

data class Restaurants(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("photograph")
    val photograph: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("cuisine_type")
    val cuisine_type: String,
    @SerializedName("operating_hours")
    val operatingHours: Map<String, String>,
    @SerializedName("reviews")
    val reviews: List<Review>,
    @SerializedName("latlng")
    val latLong: LatLong
)

data class Review(
    @SerializedName("name")
    val name: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("comments")
    val comments: String
)

data class LatLong(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)