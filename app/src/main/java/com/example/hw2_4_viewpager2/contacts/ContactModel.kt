package com.example.hw2_4_viewpager2.contacts

import android.graphics.Bitmap

data class ContactModel (
    val img: Bitmap? =null,
    val name: String,
    val number: String
    )