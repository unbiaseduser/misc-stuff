package com.sixtyninefourtwenty.stuff.listeners

import androidx.appcompat.widget.SearchView

@Suppress("unused")
fun interface ChangeOnlyQueryTextListener : SearchView.OnQueryTextListener {

    override fun onQueryTextChange(newText: String): Boolean

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    companion object {
        @JvmStatic
        fun changeOnly(block: ChangeOnlyQueryTextListener): SearchView.OnQueryTextListener = block
    }

}