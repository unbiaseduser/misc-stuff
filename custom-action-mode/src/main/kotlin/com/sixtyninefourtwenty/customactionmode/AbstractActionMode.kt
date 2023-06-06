package com.sixtyninefourtwenty.customactionmode

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.appcompat.view.SupportMenuInflater
import androidx.appcompat.widget.Toolbar
import java.util.function.BiConsumer

typealias MenuInflation = BiConsumer<Menu, MenuInflater>

@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class AbstractActionMode(private val toolbar: Toolbar,
                                  private val onBackPressedDispatcher: OnBackPressedDispatcher) {

    private val finishOnBackPressed = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }

    private var callback: Callback? = null

    /**
     * @see [closeIcon]
     */
    fun setCloseIcon(@DrawableRes iconRes: Int) {
        toolbar.setNavigationIcon(iconRes)
    }

    /**
     * Icon for the "navigation" button of the toolbar.
     *
     * This property is so-named because here the button is only used for dismissing the action mode.
     */
    var closeIcon: Drawable?
        get() = toolbar.navigationIcon
        set(value) {
            toolbar.navigationIcon = value
        }

    fun setTitle(@StringRes titleRes: Int) {
        toolbar.setTitle(titleRes)
    }

    var title: CharSequence?
        get() = toolbar.title
        set(value) {
            toolbar.title = value
        }

    fun setSubtitle(@StringRes subtitleRes: Int) {
        toolbar.setSubtitle(subtitleRes)
    }

    var subtitle: CharSequence?
        get() = toolbar.subtitle
        set(value) {
            toolbar.subtitle = value
        }

    fun isActive() = callback != null

    abstract fun show(toolbar: Toolbar)

    @SuppressLint("RestrictedApi")
    @JvmOverloads
    fun start(callback: Callback, initMenu: MenuInflation? = null) {
        if (isActive()) {
            return
        }
        this.callback = callback
        onBackPressedDispatcher.addCallback(finishOnBackPressed)
        toolbar.menu.clear()
        initMenu?.accept(toolbar.menu, SupportMenuInflater(toolbar.context))
        show(toolbar)
        callback.onActionModeStarted(this)
    }

    fun start(callback: Callback, @MenuRes menuRes: Int) = start(callback) { menu, menuInflater ->
        menuInflater.inflate(menuRes, menu)
    }

    abstract fun hide(toolbar: Toolbar)

    fun finish() {
        if (!isActive()) {
            return
        }
        val callback = callback!!
        this.callback = null
        toolbar.menu.close()
        hide(toolbar)
        finishOnBackPressed.remove()
        callback.onActionModeFinished(this)
    }

    init {
        with(toolbar) {
            visibility = View.GONE
            setNavigationOnClickListener { finish() }
            setOnMenuItemClickListener {
                callback?.onMenuItemClicked(this@AbstractActionMode, it) ?: false
            }
        }
    }

    interface Callback {
        fun onActionModeStarted(mode: AbstractActionMode) {}
        fun onMenuItemClicked(mode: AbstractActionMode, item: MenuItem): Boolean
        fun onActionModeFinished(mode: AbstractActionMode) {}
    }

}