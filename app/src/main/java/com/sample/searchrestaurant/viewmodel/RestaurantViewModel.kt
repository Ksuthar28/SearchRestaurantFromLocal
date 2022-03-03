package com.sample.searchrestaurant.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.searchrestaurant.models.MyRestaurant
import com.sample.searchrestaurant.repo.RestaurantRepository
import com.sample.searchrestaurant.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: RestaurantRepository,
) : ViewModel() {

    val restaurants = MutableLiveData<Resource<MutableList<MyRestaurant>>>()

    /*
    Handle coroutines exceptions
     */
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        restaurants.postValue(Resource.Error("Error: ${throwable.localizedMessage}"))
    }

    /*
    Return matching restaurant result
     */
    fun getRestaurants(searchQuery: String, context: Context) =
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            restaurants.postValue(Resource.Loading())
            val response = repository.getAllRestaurants(searchQuery, context)
            restaurants.postValue(response)
        }

}