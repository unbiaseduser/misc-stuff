@file:JvmName("Preferences")
package com.sixtyninefourtwenty.custompreferences

import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

fun PreferenceFragmentCompat.handleDisplayPreferenceDialog(preference: Preference) {
    if (preference is AbstractCustomDialogPreference) {
        preference.displayDialog(this)
    } else {
        onDisplayPreferenceDialog(preference)
    }
}