package com.sample.searchrestaurant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.searchrestaurant.R
import com.sample.searchrestaurant.databinding.ItemRestaurantBinding
import com.sample.searchrestaurant.models.MyRestaurant
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject


@FragmentScoped
class RestaurantAdapter @Inject constructor() :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<MyRestaurant>() {
        override fun areItemsTheSame(oldItem: MyRestaurant, newItem: MyRestaurant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyRestaurant, newItem: MyRestaurant): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RestaurantViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_restaurant,
            parent,
            false
        )
    )

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.binding.restaurant = differ.currentList[position]
        holder.binding.divider.visibility =
            if (position == differ.currentList.size-1) View.GONE else View.VISIBLE
    }

    inner class RestaurantViewHolder(val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root)
}