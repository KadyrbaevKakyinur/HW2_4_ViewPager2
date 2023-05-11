package com.example.hw2_4_viewpager2.UI

import android.app.Application
import android.content.SharedPreferences

class App: Application() {
    private lateinit var preferences: SharedPreferences

    companion object {
        lateinit var prefs :Prefs
    }

    override fun onCreate() {
        super.onCreate()
        preferences = this.applicationContext.getSharedPreferences("settings", MODE_PRIVATE)
        prefs = Prefs(preferences)
    }
}