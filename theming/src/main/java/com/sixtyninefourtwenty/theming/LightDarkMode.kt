package com.sixtyninefourtwenty.theming

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

internal enum class LightDarkMode(private val androidInt: Int) {
    LIGHT(AppCompatDelegate.MODE_NIGHT_NO),
    DARK(AppCompatDelegate.MODE_NIGHT_YES),
    BATTERY(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY),
    SYSTEM(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

    internal fun getPrefValue(context: Context): String {
        val arrayRes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) R.array.themes_preference_entry_values else R.array.themes_preference_entry_values_p
        return context.resources.getStringArray(arrayRes)[ordinal]
    }

    internal fun apply() {
        AppCompatDelegate.setDefaultNightMode(androidInt)
    }
}