package com.example.hw2_4_viewpager2.UI

import android.content.SharedPreferences

class Prefs(private  val preferences: SharedPreferences) {
    fun saveBoardState (){
        preferences.edit().putBoolean("key",true).apply()
    }

    fun isBoardShow(): Boolean{
        return preferences.getBoolean("key",false)
    }

}