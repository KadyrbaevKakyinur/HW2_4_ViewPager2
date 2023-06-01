package com.example.hw2_4_viewpager2.UI

import android.annotation.SuppressLint
import android.content.SharedPreferences

class Prefs(private  val preferences: SharedPreferences) {
    fun saveBoardState() {
        preferences.edit().putBoolean("isShow", true).apply()
    }
    @SuppressLint("CommitPrefEdits")
    fun logOut() {
        preferences.edit().clear().apply()
    }

    fun isBoardShow(): Boolean {
        return preferences.getBoolean("isShow", false)
    }
    fun saveUserMod(){
        preferences.edit().putBoolean("registered",true).apply ()
    }
    fun isRegisterShowed():Boolean{
        return preferences.getBoolean("registered",false)
    }

    @SuppressLint("CommitPrefEdits")
    fun saveUserName(name: String) {
        preferences.edit().putString("name", name).apply()
    }

    fun getUserName(): String {
        return preferences.getString("name", "Unknown")!!
    }

    fun saveSurUserName(name: String) {
        preferences.edit().putString("sur_user_name", name).apply()
    }

    fun getSurUserName(): String {
        return preferences.getString("sur_user_name", "Unknown")!!
    }

    fun saveUserPhone(number: Int) {
        preferences.edit().putInt("number", number).apply()
    }

    fun getUserNumber(): Int {
        return preferences.getInt("number", 1)
    }

    fun isUserMarried(isMarried: Boolean): Boolean {
        preferences.edit().putBoolean("isMaried", isMarried).apply()
        return isMarried
    }

    fun getUserMarried(): Boolean {
        return preferences.getBoolean("isMaried", false)
    }

}


