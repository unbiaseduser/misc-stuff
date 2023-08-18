@file:JvmName("Views")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.tabs.TabLayout
import com.sixtyninefourtwenty.stuff.annotations.NotSuitableForJava

fun EditText.isBlank() = text == null || text.toString().isBlank()

fun EditText.getInput() = text?.toString()?.trim().orEmpty()

@NotSuitableForJava(reason = "Property wrapper for getDrawable() and setImageDrawable().")
var ImageView.drawableContent: Drawable?
    get() = drawable
    set(value) { setImageDrawable(value) }

enum class ViewVisibility(val androidInt: Int) {
    VISIBLE(View.VISIBLE), INVISIBLE(View.INVISIBLE), GONE(View.GONE);
}

var View.visibilityAsEnum: ViewVisibility
    get() = ViewVisibility.entries.first { it.androidInt == visibility }
    set(value) { visibility = value.androidInt }

@JvmOverloads
fun View.toggleVisibility(visibilityToToggleTo: ViewVisibility = ViewVisibility.GONE) {
    require(visibilityToToggleTo != ViewVisibility.VISIBLE) {
        "visibilityToToggleTo must be ${ViewVisibility.GONE} or ${ViewVisibility.INVISIBLE}"
    }
    visibilityAsEnum = if (visibilityAsEnum == ViewVisibility.VISIBLE) {
        visibilityToToggleTo
    } else {
        ViewVisibility.VISIBLE
    }
}

enum class TabLayoutTabMode(val androidInt: Int) {
    FIXED(TabLayout.MODE_FIXED), SCROLLABLE(TabLayout.MODE_SCROLLABLE), AUTO(TabLayout.MODE_AUTO);
}

var TabLayout.tabModeAsEnum: TabLayoutTabMode
    get() = TabLayoutTabMode.entries.first { it.androidInt == tabMode }
    set(value) { tabMode = value.androidInt }
