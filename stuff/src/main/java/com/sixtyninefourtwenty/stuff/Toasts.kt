@file:JvmName("Toasts")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

enum class ToastDuration(val androidInt: Int) {
    SHORT(Toast.LENGTH_SHORT), LONG(Toast.LENGTH_LONG);

    internal companion object {
        val VALUES = listOf(*values())
    }
}

var Toast.durationAsEnum: ToastDuration
    get() = ToastDuration.VALUES.first { it.androidInt == duration }
    set(value) { duration = value.androidInt }

@JvmOverloads
fun Context.makeToast(text: CharSequence, duration: ToastDuration = ToastDuration.SHORT): Toast =
    Toast.makeText(this, text, duration.androidInt)

@JvmOverloads
fun Context.makeToast(@StringRes textRes: Int, duration: ToastDuration = ToastDuration.SHORT): Toast =
    Toast.makeText(this, textRes, duration.androidInt)
