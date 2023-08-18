@file:JvmName("ThemingSettings")

package com.sixtyninefourtwenty.theming

import android.app.Activity
import android.content.Context
import com.sixtyninefourtwenty.theming.preferences.ThemingPreferences

private fun Activity.doStuffAndRecreate(block: Activity.() -> Unit) {
    block(this)
    recreate()
}

@Deprecated(message = "This property creates new objects every time it's called. Please create a single instance of ThemingPreferencesFacade and reuse it.")
var Context.md3Setting: Boolean
    get() = ThemingPreferences(this).md3
    set(value) { ThemingPreferences(this).md3 = value }

@Deprecated(message = "See Context.md3Setting")
fun Activity.setMd3SettingAndRecreate(md3: Boolean) = doStuffAndRecreate {
    md3Setting = md3
}

@Deprecated(message = "This property creates new objects every time it's called. Please create a single instance of ThemingPreferencesFacade and reuse it.")
var Context.themeColorSetting: ThemeColor
    get() = ThemingPreferences(this).themeColor
    set(value) { ThemingPreferences(this).themeColor = value }

@Deprecated(message = "See Context.themeColorSetting")
fun Activity.setThemeColorSettingAndRecreate(color: ThemeColor) = doStuffAndRecreate {
    themeColorSetting = color
}

@Deprecated(message = "This property creates new objects every time it's called. Please create a single instance of ThemingPreferencesFacade and reuse it.")
var Context.lightDarkModeSetting: LightDarkMode
    get() = ThemingPreferences(this).lightDarkMode
    set(value) { ThemingPreferences(this).lightDarkMode = value }

@Deprecated(message = "See Context.lightDarkModeSetting")
fun Activity.setLightDarkModeSettingAndRecreate(mode: LightDarkMode) = doStuffAndRecreate {
    lightDarkModeSetting = mode
}
