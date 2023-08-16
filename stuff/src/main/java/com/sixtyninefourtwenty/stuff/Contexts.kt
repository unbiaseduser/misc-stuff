@file:JvmName("Contexts")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.annotation.LayoutRes
import java.util.function.Consumer

fun Context.startActivity(intent: Intent, exceptionHandler: Consumer<in ActivityNotFoundException>) {
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        exceptionHandler.accept(e)
    }
}

fun Context.newRemoteViews(@LayoutRes layoutRes: Int) = RemoteViews(packageName, layoutRes)
