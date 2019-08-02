package com.tinoba.template.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Drawable.setColorTheme(@ColorRes colorRes: Int, context: Context) {
    setColorFilter(ContextCompat.getColor(context, colorRes), PorterDuff.Mode.SRC_ATOP)
}