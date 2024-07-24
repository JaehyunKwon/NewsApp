package com.test.mobile.extensions

import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


fun screenWidthDp(context: Context?): Float {
    val resources = context!!.resources
    val metrics = resources.displayMetrics
    return metrics.widthPixels / metrics.density
}

fun convertDateTime(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC") // 'Z' indicates UTC time zone
    val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    return try {
        val date = inputFormat.parse(inputDate)
        outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}