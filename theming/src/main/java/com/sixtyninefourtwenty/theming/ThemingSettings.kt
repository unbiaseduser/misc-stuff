@file:JvmName("ThemingSettings")

package com.sixtyninefourtwenty.theming

import android.app.Activity
import android.content.Context
import com.sixtyninefourtwenty.theming.preferences.ThemingPreferences

private fun Activity.doStuffAndRecreate(block: Activity.() -> Unit) {
    block(this)
    recreate()
}

var Context.md3Setting: Boolean
    get() = ThemingPreferences(this).md3
    set(value) { ThemingPreferences(this).md3 = value }

fun Activity.setMd3SettingAndRecreate(md3: Boolean) = doStuffAndRecreate {
    md3Setting = md3
}

var Context.themeColorSetting: ThemeColor
    get() = ThemingPreferences(this).themeColor
    set(value) { ThemingPreferences(this).themeColor = value }

fun Activity.setThemeColorSettingAndRecreate(color: ThemeColor) = doStuffAndRecreate {
    themeColorSetting = color
}

var Context.lightDarkModeSetting: LightDarkMode
    get() = ThemingPreferences(this).lightDarkMode
    set(value) { ThemingPreferences(this).lightDarkMode = value }

fun Activity.setLightDarkModeSettingAndRecreate(mode: LightDarkMode) = doStuffAndRecreate {
    lightDarkModeSetting = mode
}
