package com.example.kotlin.utils

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class PicassUtils(mContext: Context) {
    fun load(url: String, view: ImageView) {
        Picasso.get().load(url).fit().centerInside().into(view)
    }

    fun load(url: String, view: ImageView, callback: Callback) {
        Picasso.get().load(url).fit().centerInside().into(view, callback)
    }
}