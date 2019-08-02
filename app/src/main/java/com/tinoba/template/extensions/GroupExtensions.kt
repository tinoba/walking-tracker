package com.tinoba.template.extensions

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group

/**
 * This extension is used for setting the Group visibility. This is due to the bug in the current com.android.support.constraint:constraint-layout
 * library (in the time: 1.1.0-beta5)
 * Check https://stackoverflow.com/questions/47865436/cant-set-visibility-on-constraint-group
 */
fun Group.setGroupVisibility(func: () -> Int) {
    visibility = ConstraintLayout.GONE
    this.visibility = func()
}