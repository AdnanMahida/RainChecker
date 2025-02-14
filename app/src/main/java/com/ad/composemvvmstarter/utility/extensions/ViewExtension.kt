package com.ad.composemvvmstarter.utility.extensions

import android.view.HapticFeedbackConstants
import android.view.View

fun View.weakHapticFeedback() {
    this.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
}

fun View.strongHapticFeedback() {
    this.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
}