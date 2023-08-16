@file:JvmName("BottomSheetDialogs")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

val BottomSheetDialogFragment.bottomSheetDialog: BottomSheetDialog?
    get() = dialog as BottomSheetDialog?

fun BottomSheetDialogFragment.requireBottomSheetDialog() = requireDialog() as BottomSheetDialog

enum class BottomSheetBehaviorState(val androidInt: Int) {
    EXPANDED(BottomSheetBehavior.STATE_EXPANDED),
    COLLAPSED(BottomSheetBehavior.STATE_COLLAPSED),
    DRAGGING(BottomSheetBehavior.STATE_DRAGGING),
    SETTLING(BottomSheetBehavior.STATE_SETTLING),
    HIDDEN(BottomSheetBehavior.STATE_HIDDEN),
    HALF_EXPANDED(BottomSheetBehavior.STATE_HALF_EXPANDED);

    internal companion object {
        val VALUES = listOf(*values())
    }
}

/**
 * Getter is applicable for [BottomSheetBehaviorState.EXPANDED], [BottomSheetBehaviorState.HALF_EXPANDED], [BottomSheetBehaviorState.COLLAPSED],
 * [BottomSheetBehaviorState.DRAGGING] and [BottomSheetBehaviorState.SETTLING].
 *
 * Setter is applicable for [BottomSheetBehaviorState.COLLAPSED], [BottomSheetBehaviorState.EXPANDED], [BottomSheetBehaviorState.HIDDEN]
 * and [BottomSheetBehaviorState.HALF_EXPANDED].
 * @see BottomSheetBehavior.getState
 * @see BottomSheetBehavior.setState
 */
var BottomSheetBehavior<*>.stateAsEnum: BottomSheetBehaviorState
    get() = BottomSheetBehaviorState.VALUES.first { it.androidInt == state }
    set(value) { state = value.androidInt }
