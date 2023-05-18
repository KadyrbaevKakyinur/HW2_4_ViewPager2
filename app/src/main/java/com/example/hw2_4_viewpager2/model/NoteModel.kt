package com.example.hw2_4_viewpager2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NoteModel (    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var title: String,
    var des: String,
    var date: String)
