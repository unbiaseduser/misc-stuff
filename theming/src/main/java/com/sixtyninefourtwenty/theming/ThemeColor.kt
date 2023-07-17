package com.sixtyninefourtwenty.theming

import android.content.Context
import android.content.res.Resources
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat

internal enum class ThemeColor(@StyleRes private val themeColorStyleRes: Int, @ColorRes val colorRes: Int) {
    BLUE(R.style.ThemeColors_Blue, R.color.m2ColorPrimary_blue),
    RED(R.style.ThemeColors_Red, R.color.m2ColorPrimary_red),
    GREEN(R.style.ThemeColors_Green, R.color.m2ColorPrimary_green),
    PURPLE(R.style.ThemeColors_Purple, R.color.m2ColorPrimary_purple),
    ORANGE(R.style.ThemeColors_Orange, R.color.m2ColorPrimary_orange),
    PINK(R.style.ThemeColors_Pink, R.color.m2ColorPrimary_pink);

    fun getColorInt(context: Context) = ContextCompat.getColor(context, colorRes)

    fun applyTo(theme: Resources.Theme) {
        theme.applyStyle(themeColorStyleRes, true)
    }
}