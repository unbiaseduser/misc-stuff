package com.sixtyninefourtwenty.bottomsheetalertdialog.internal

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.StringRes
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.sixtyninefourtwenty.bottomsheetalertdialog.ButtonAppearanceProperties
import com.sixtyninefourtwenty.bottomsheetalertdialog.DialogButton

sealed interface BottomSheetAlertDialogCommon {
    val context: Context
    val root: View
    val title: TextView
    val content: ScrollView
    val buttonContainer: RelativeLayout
    val positiveButton: Button
    val neutralButton: Button
    val negativeButton: Button

    fun setTitle(@StringRes titleRes: Int) {
        with(title) {
            visibility = View.VISIBLE
            setText(titleRes)
        }
    }

    fun setContentView(view: View) {
        with(content) {
            removeAllViews()
            addView(view)
        }
    }

    fun getButton(whichButton: DialogButton): Button {
        return when (whichButton) {
            DialogButton.POSITIVE -> positiveButton
            DialogButton.NEGATIVE -> negativeButton
            DialogButton.NEUTRAL -> neutralButton
        }
    }

    fun setButtonAppearance(whichButton: DialogButton, properties: ButtonAppearanceProperties) {
        with(getButton(whichButton)) {
            visibility = View.VISIBLE
            when {
                properties.textRes != 0 -> setText(properties.textRes)
                properties.text != null -> text = properties.text
            }
        }
    }

    companion object {
        fun create(context: Context, isFullscreen: Boolean) =
            if (isFullscreen) BottomSheetAlertDialogFullscreenUi(context) else BottomSheetAlertDialogNotFullscreenUi(context)
    }

}

private class BottomSheetAlertDialogFullscreenUi(override val context: Context) : BottomSheetAlertDialogCommon {
    override val title: TextView = MaterialTextView(context).apply {
        @Suppress("DEPRECATION")
        setTextAppearance(context, materialHeadlineStyleRes)
        ellipsize = TextUtils.TruncateAt.END
        gravity = Gravity.CENTER
        visibility = View.GONE
    }
    override val positiveButton: Button = MaterialButton(context).apply {
        text = context.getString(android.R.string.ok)
        visibility = View.GONE
    }
    override val neutralButton: Button = context.createBorderlessButton().apply {
        text = context.getString(android.R.string.cancel)
        visibility = View.GONE
    }
    override val negativeButton: Button = context.createBorderlessButton().apply {
        text = context.getString(android.R.string.cancel)
        visibility = View.GONE
    }
    override val buttonContainer: RelativeLayout = RelativeLayout(context).apply {
        addView(positiveButton, RelativeLayout.LayoutParams(wrapContent, wrapContent).apply {
            addRule(RelativeLayout.ALIGN_PARENT_END)
            topMargin = pxToDp(12).toInt()
            marginEnd = pxToDp(16).toInt()
        })
        addView(neutralButton, RelativeLayout.LayoutParams(wrapContent, wrapContent).apply {
            addRule(RelativeLayout.ALIGN_PARENT_START)
            topMargin = pxToDp(12).toInt()
            marginStart = pxToDp(16).toInt()
        })
        addView(negativeButton, RelativeLayout.LayoutParams(wrapContent, wrapContent).apply {
            addRule(RelativeLayout.START_OF, positiveButton.ensureAvailableId())
            topMargin = pxToDp(12).toInt()
            marginEnd = pxToDp(8).toInt()
        })
    }
    override val content: ScrollView = ScrollView(context)
    override val root: RelativeLayout = RelativeLayout(context).apply {
        layoutParams = RelativeLayout.LayoutParams(matchParent, wrapContent).apply {
            setPadding(0, pxToDp(16).toInt(), 0, pxToDp(16).toInt())
        }
        addView(title, RelativeLayout.LayoutParams(matchParent, wrapContent).apply {
            marginEnd = pxToDp(16).toInt()
            marginStart = pxToDp(16).toInt()
            bottomMargin = pxToDp(16).toInt()
        })
        addView(content, RelativeLayout.LayoutParams(matchParent, wrapContent).apply {
            addRule(RelativeLayout.BELOW, title.ensureAvailableId())
            addRule(RelativeLayout.ABOVE, buttonContainer.ensureAvailableId())
        })
        addView(buttonContainer, RelativeLayout.LayoutParams(matchParent, wrapContent).apply {
            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        })
    }

}

private class BottomSheetAlertDialogNotFullscreenUi(override val context: Context) : BottomSheetAlertDialogCommon {
    override val title: TextView = MaterialTextView(context).apply {
        @Suppress("DEPRECATION")
        setTextAppearance(context, materialHeadlineStyleRes)
        ellipsize = TextUtils.TruncateAt.END
        gravity = Gravity.CENTER
        visibility = View.GONE
    }
    override val positiveButton: Button = MaterialButton(context).apply {
        text = context.getString(android.R.string.ok)
        visibility = View.GONE
    }
    override val neutralButton: Button = context.createBorderlessButton().apply {
        text = context.getString(android.R.string.cancel)
        visibility = View.GONE
    }
    override val negativeButton: Button = context.createBorderlessButton().apply {
        text = context.getString(android.R.string.cancel)
        visibility = View.GONE
    }
    override val buttonContainer: RelativeLayout = RelativeLayout(context).apply {
        id = View.generateViewId()
        addView(positiveButton, RelativeLayout.LayoutParams(wrapContent, wrapContent).apply {
            addRule(RelativeLayout.ALIGN_PARENT_END)
            topMargin = pxToDp(12).toInt()
            marginEnd = pxToDp(16).toInt()
        })
        addView(neutralButton, RelativeLayout.LayoutParams(wrapContent, wrapContent).apply {
            addRule(RelativeLayout.ALIGN_PARENT_START)
            topMargin = pxToDp(12).toInt()
            marginStart = pxToDp(16).toInt()
        })
        addView(negativeButton, RelativeLayout.LayoutParams(wrapContent, wrapContent).apply {
            addRule(RelativeLayout.START_OF, positiveButton.ensureAvailableId())
            topMargin = pxToDp(12).toInt()
            marginEnd = pxToDp(8).toInt()
        })
    }
    override val content: ScrollView = ScrollView(context)
    override val root: LinearLayout = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent).apply {
            setPadding(0, pxToDp(16).toInt(), 0, pxToDp(16).toInt())
        }
        addView(title, LinearLayout.LayoutParams(matchParent, wrapContent).apply {
            marginEnd = pxToDp(16).toInt()
            marginStart = pxToDp(16).toInt()
            bottomMargin = pxToDp(16).toInt()
        })
        addView(content, LinearLayout.LayoutParams(matchParent, wrapContent))
        addView(buttonContainer, LinearLayout.LayoutParams(matchParent, wrapContent))
    }

}
