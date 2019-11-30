package com.whompum.uitoolbox.Utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.whompum.uitoolbox.R

/**
 * Progmmatic way to have a single method responsible for resolving a error-snackbar
 * A key attribute is the background highlight, which has to be set progmatically, and to ensure
 * consitent theming, we'll only set according to the specified theme.
 */
fun makeErrorSnackbar(v: View, msg: String): Snackbar {
    val snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_SHORT)

    val typedValue = readAttribute(R.attr.mColorError, v.context.theme)

    typedValue?.let {
        snackbar.setBackgroundTint(it.data)
    }

    return snackbar
}

/**
 * Reads a single attribute from the specified theme
 */
fun readAttribute(attribute: Int, theme: Resources.Theme): TypedValue? {
    val typedValue = TypedValue()

    theme.resolveAttribute(attribute, typedValue, true)

    return typedValue
}

fun getScreenWidth(ctx: Context) = ctx.resources.displayMetrics.widthPixels

fun getScreenWidthOffset(ctx: Context, offsetAttribute: Int): Int {
    val marginValue = readAttribute(offsetAttribute, ctx.theme)

    var size = getScreenWidth(ctx)

    marginValue?.let {
        size -= it.data
    }

    return size
}

