package com.balajiprabhu.trendingrepositories.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("android:loadIconUrl")
    fun setLoadIconUrl(view: View, icon: String) {
        val imageView: ImageView = view as ImageView
        Glide.with(view.context)
            .load(icon)
            .into(imageView)

    }

}