package com.app.studye.utils

import android.content.Context

class SharedPreferenceHelper(private val context: Context) {

    companion object {
        private const val MY_PREF_KEY = "MY_PREF"
    }

    fun saveStringData(key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getStringData(key: String): String? {
        val sharedPreferences = context.getSharedPreferences(MY_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }
}
