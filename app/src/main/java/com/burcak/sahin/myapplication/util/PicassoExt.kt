package com.burcak.sahin.myapplication.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.updateWithUrl(url: String, imageViewAvatar: ImageView) {
    if (!url.isNullOrEmpty()) {
        Picasso.get()
            .load(url)
            .fit()
            .into(imageViewAvatar)
    }
}
