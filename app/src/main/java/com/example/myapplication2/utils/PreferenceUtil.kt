package com.example.myapplication2.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil private constructor(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var instance: PreferenceUtil? = null

        fun getInstance(context: Context): PreferenceUtil {
            return instance ?: synchronized(this) {
                instance ?: PreferenceUtil(context.applicationContext).also {
                    instance = it
                }
            }
        }
    }

    fun putString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return preferences.getString(key, defaultValue) ?: defaultValue
    }

    fun putBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }
}
