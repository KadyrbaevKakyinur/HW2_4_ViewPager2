package com.example.hw2_4_viewpager2.UI

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageWithGlide(url : Uri){
    Glide.with(this).load(url).centerCrop().into(this)
}