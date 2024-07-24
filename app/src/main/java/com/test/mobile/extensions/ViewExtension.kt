package com.test.mobile.extensions

import android.content.Context


fun screenWidthDp(context: Context?): Float {
    val resources = context!!.resources
    val metrics = resources.displayMetrics
    return metrics.widthPixels / metrics.density
}