package com.example.hw2_4_viewpager2.activiti

import android.app.Application
import android.content.SharedPreferences
import com.example.hw2_4_viewpager2.UI.Prefs
import com.example.hw2_4_viewpager2.fragment.note.NoteDataBase

class App: Application() {
    private lateinit var preferences: SharedPreferences

    companion object {
        lateinit var prefs : Prefs
        lateinit var note:NoteDataBase
    }

    override fun onCreate() {
        super.onCreate()
        preferences = this.applicationContext.getSharedPreferences("settings", MODE_PRIVATE)
        prefs = Prefs(preferences)


        note= NoteDataBase(this.applicationContext)
    }
}