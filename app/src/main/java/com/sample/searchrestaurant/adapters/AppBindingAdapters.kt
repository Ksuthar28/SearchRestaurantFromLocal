package com.sample.searchrestaurant.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sample.searchrestaurant.R
import java.util.*

/*
Load image from url
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.preview)
            .into(view)
    } else {
        Glide.with(view.context)
            .clear(view)
        view.setImageResource(R.drawable.preview)
    }
}

/*
Capitalize first letter of each word
 */
@BindingAdapter("capWord")
fun textCapWord(textView: TextView, text: String) {
    val string: String = text.split(" ").joinToString(" ") { world ->
        world.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
    }
    textView.text = string
}
