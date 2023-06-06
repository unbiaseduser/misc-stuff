package com.sixtyninefourtwenty.customactionmode

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.widget.Toolbar

@Suppress("unused")
class FadingActionMode(toolbar: Toolbar, onBackPressedDispatcher: OnBackPressedDispatcher) : AbstractActionMode(toolbar, onBackPressedDispatcher) {

    override fun show(toolbar: Toolbar) {
        toolbar.alpha = 0F
        toolbar.visibility = View.VISIBLE
        toolbar.animate()
            .setDuration(toolbar.resources.getInteger(android.R.integer.config_shortAnimTime).toLong())
            .alpha(1F)
            //for some reason, without this the bar just disappears at the end of the animation
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    toolbar.visibility = View.VISIBLE
                }
            })
            .start()
    }

    override fun hide(toolbar: Toolbar) {
        toolbar.animate()
            .setDuration(toolbar.resources.getInteger(android.R.integer.config_shortAnimTime).toLong())
            .alpha(0F)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    toolbar.visibility = View.GONE
                }
            })
            .start()
    }
}