package com.test.mobile.extensions

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.test.mobile.R
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

fun ImageView.loadImageUrl(url: String?) {
    val options = RequestOptions()
        .error(R.drawable.ic_launcher_foreground) // 에러 발생 시 표시할 이미지
        .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐시 전략

    if (!url.isNullOrEmpty()) {
        Glide.with(this.context)
            .load(url)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    } else {
        Glide.with(this.context)
            .load(R.drawable.ic_launcher_foreground) // 기본 이미지
            .apply(options)
            .into(this)
    }
}