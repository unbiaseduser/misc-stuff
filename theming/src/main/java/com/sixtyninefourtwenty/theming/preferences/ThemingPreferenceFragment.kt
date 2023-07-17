package com.sixtyninefourtwenty.theming.preferences

import android.os.Build
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.sixtyninefourtwenty.custompreferences.AbstractCustomDialogPreference
import com.sixtyninefourtwenty.custompreferences.PredefinedColorPickerPreference
import com.sixtyninefourtwenty.theming.R

class ThemingPreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = ThemingPreferences(requireContext())
        preferenceScreen = preferenceManager.createPreferenceScreen(requireContext()).apply {
            addPreference(SwitchPreferenceCompat(requireContext()).apply {
                key = ThemingPreferences.MD3_KEY
                title = getString(R.string.md3)
                summary = getString(R.string.md3_pref_summary)
                setDefaultValue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                setOnPreferenceChangeListener { _, _ ->
                    requireActivity().recreate()
                    true
                }
            })
            addPreference(ListPreference(requireContext()).apply {
                key = ThemingPreferences.LIGHT_DARK_MODE_KEY
                title = getString(R.string.theme)
                dialogTitle = getString(R.string.theme)
                entries = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) requireContext().resources.getStringArray(R.array.themes_preference_entries) else requireContext().resources.getStringArray(R.array.themes_preference_entries_p)
                entryValues = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) requireContext().resources.getStringArray(R.array.themes_preference_entry_values) else requireContext().resources.getStringArray(R.array.themes_preference_entry_values_p)
                setDefaultValue(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) "system" else "light")
                setOnPreferenceChangeListener { _, _ ->
                    requireActivity().recreate()
                    true
                }
                summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
            })
            addPreference(PredefinedColorPickerPreference(requireContext()).apply {
                key = ThemingPreferences.PRIMARY_COLOR_KEY
                title = getString(R.string.primary_color)
                setDefaultValue("#3385ff")
                setAvailableColorsArrayRes(R.array.theme_color_preference_available_colors)
                setOnPreferenceChangeListener { _, _ ->
                    requireActivity().recreate()
                    true
                }
                summaryProvider = PredefinedColorPickerPreference.SUMMARY_PROVIDER
            })
        }
    }

    override fun onDisplayPreferenceDialog(preference: Preference) {
        if (preference is AbstractCustomDialogPreference) {
            preference.displayDialog(this)
        } else {
            super.onDisplayPreferenceDialog(preference)
        }
    }

}