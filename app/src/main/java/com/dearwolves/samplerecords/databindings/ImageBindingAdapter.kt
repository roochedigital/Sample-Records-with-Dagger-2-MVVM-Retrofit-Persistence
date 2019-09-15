package com.dearwolves.samplerecords.databindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.dearwolves.core.utilities.GlideApp

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:load_img")
    fun setImageUrl(view: ImageView, url: String) {

        val circularProgressDrawable = CircularProgressDrawable(view.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        GlideApp.with(view)
            .load(url)
            .placeholder(circularProgressDrawable)
            .into(view)
    }
}