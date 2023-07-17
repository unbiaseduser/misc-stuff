package com.sixtyninefourtwenty.custompreferences

import android.content.Context
import android.content.SharedPreferences
import android.content.res.TypedArray
import android.os.Parcel
import android.os.Parcelable
import android.text.format.DateFormat
import android.util.AttributeSet
import androidx.fragment.app.DialogFragment
import androidx.preference.Preference.SummaryProvider
import com.google.android.material.timepicker.MaterialTimePicker
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TimePickerPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.preference.R.attr.dialogPreferenceStyle,
    defStyleRes: Int = 0
) : AbstractCustomDialogPreference(context, attrs, defStyleAttr, defStyleRes) {

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.TimePickerPreference, defStyleAttr, defStyleRes)
        if (a.getBoolean(R.styleable.TimePickerPreference_useSimpleSummaryProvider, false)) {
            summaryProvider = mySummaryProvider
        }
        a.recycle()
    }

    var time: LocalTime? = null
        set(value) {
            if (callChangeListener(value)) {
                field = value
                persistString(value?.let(timeFormatPattern::format))
                notifyChanged()
            }
        }

    override fun createDialog(): DialogFragment {
        return MaterialTimePicker.Builder()
            .setTitleText(dialogTitle)
            .setHour(time?.hour ?: 0)
            .setMinute(time?.minute ?: 0)
            .build()
            .apply {
                addOnPositiveButtonClickListener {
                    time = LocalTime.of(hour, minute)
                }
            }
    }

    override fun onGetDefaultValue(a: TypedArray, index: Int): Any? {
        return a.getString(index)
    }

    override fun onSetInitialValue(defaultValue: Any?) {
        val actualDefaultValue = defaultValue as String?
        val value: String? = getPersistedString(actualDefaultValue)
        time = value?.let { LocalTime.parse(it, timeFormatPattern) }
        persistString(value)
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        if (isPersistent) {
            return superState
        }

        return SavedState(superState).also {
            it.time = this.time
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state !is SavedState) {
            super.onRestoreInstanceState(state)
            return
        }

        super.onRestoreInstanceState(state.superState)
        this.time = state.time
    }

    private class SavedState : BaseSavedState {

        constructor(source: Parcel) : super(source) {
            time = source.readSerializable() as LocalTime?
        }

        constructor(superState: Parcelable?) : super(superState)

        var time: LocalTime? = null

        override fun writeToParcel(dest: Parcel, flags: Int) {
            super.writeToParcel(dest, flags)
            dest.writeSerializable(time)
        }

        companion object CREATOR : Parcelable.Creator<SavedState> {
            override fun createFromParcel(source: Parcel): SavedState {
                return SavedState(source)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }

        }
    }

    companion object {
        fun getPersistedLocalTime(preferences: SharedPreferences, key: String): LocalTime? = preferences.getString(key, null)?.let { LocalTime.parse(it, timeFormatPattern) }
        private val timeFormatPattern: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        private val timeFormatPattern12h: DateTimeFormatter = DateTimeFormatter.ofPattern("KK:mm a")
        private val mySummaryProvider: SummaryProvider<TimePickerPreference> by lazy {
            SummaryProvider<TimePickerPreference> {
                val time = it.time
                return@SummaryProvider if (time != null) {
                    if (DateFormat.is24HourFormat(it.context)) {
                        timeFormatPattern.format(time)
                    } else {
                        timeFormatPattern12h.format(time)
                    }
                } else {
                    it.context.getString(androidx.preference.R.string.not_set)
                }
            }
        }
    }

}