package com.sixtyninefourtwenty.stuff.listeners

import android.view.View

@Suppress("unused")
fun interface ShortedOnLongClickListener : View.OnLongClickListener {

    override fun onLongClick(v: View?): Boolean {
        onClickCustom()
        return true
    }

    fun onClickCustom()

    companion object {
        @JvmStatic
        fun shorten(block: ShortedOnLongClickListener): View.OnLongClickListener = block
    }

}