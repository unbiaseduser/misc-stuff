@file:JvmName("Snackbars")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

enum class SnackbarDuration(val androidInt: Int) {
    SHORT(BaseTransientBottomBar.LENGTH_SHORT),
    LONG(BaseTransientBottomBar.LENGTH_LONG),
    INDEFINITE(BaseTransientBottomBar.LENGTH_INDEFINITE);
}

var Snackbar.durationAsEnum: SnackbarDuration
    get() = SnackbarDuration.entries.first { it.androidInt == duration }
    set(value) { duration = value.androidInt }

@JvmOverloads
fun View.makeSnackbar(text: CharSequence, duration: SnackbarDuration = SnackbarDuration.SHORT) =
    Snackbar.make(this, text, duration.androidInt)

@JvmOverloads
fun View.makeSnackbar(@StringRes textRes: Int, duration: SnackbarDuration = SnackbarDuration.SHORT) =
    Snackbar.make(this, textRes, duration.androidInt)

@JvmOverloads
fun View.makeSnackbar(context: Context, text: CharSequence, duration: SnackbarDuration = SnackbarDuration.SHORT) =
    Snackbar.make(context, this, text, duration.androidInt)
