@file:JvmSynthetic
package com.sixtyninefourtwenty.bottomsheetalertdialog.internal

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Px
import com.google.android.material.button.MaterialButton

internal val materialHeadlineStyleRes: Int
    get() = com.google.android.material.R.style.TextAppearance_MaterialComponents_Headline5

internal fun Context.createBorderlessButton() = MaterialButton(this, null, com.google.android.material.R.attr.borderlessButtonStyle)

internal fun View.ensureAvailableId(): Int {
    if (id != View.NO_ID) {
        return id
    }
    id = View.generateViewId()
    return id
}

internal fun View.pxToDp(@Px px: Int) = (resources.displayMetrics.density * px)

internal inline val View.matchParent
    get() = ViewGroup.LayoutParams.MATCH_PARENT

internal inline val View.wrapContent
    get() = ViewGroup.LayoutParams.WRAP_CONTENT