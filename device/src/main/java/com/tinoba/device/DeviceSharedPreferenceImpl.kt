package com.tinoba.device

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("ApplySharedPref")
class DeviceSharedPreferenceImpl(context: Context) : DeviceSharedPreference {

    companion object {

        private const val SHARED_PREFS_FILE_NAME = "device_preferences"
    }

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE)
    }

    override fun clear() = sharedPreferences.edit()
            .clear()
            .apply()
}