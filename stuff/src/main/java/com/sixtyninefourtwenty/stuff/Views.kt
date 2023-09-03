@file:JvmName("Views")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.sixtyninefourtwenty.stuff.annotations.NotSuitableForJava

fun EditText.isBlank() = text == null || text.toString().isBlank()

fun EditText.getInput() = text?.toString()?.trim().orEmpty()

@NotSuitableForJava(reason = "Property wrapper for getDrawable() and setImageDrawable().")
var ImageView.drawableContent: Drawable?
    get() = drawable
    set(value) { setImageDrawable(value) }

@JvmOverloads
fun View.toggleVisibility(visibilityToToggleTo: Int = View.GONE) {
    require(visibilityToToggleTo != View.VISIBLE) {
        "visibilityToToggleTo must be View.GONE or View.VISIBLE"
    }
    visibility = if (visibility == View.VISIBLE) {
        visibilityToToggleTo
    } else {
        View.VISIBLE
    }
}
