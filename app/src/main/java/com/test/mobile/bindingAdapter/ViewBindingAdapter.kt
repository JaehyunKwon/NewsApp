package com.test.mobile.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.remember.mobile.newsapp.R

object ViewBindingAdapter {
    @BindingAdapter("loadImageUrl")
    @JvmStatic
    fun loadImageUrl(view: ImageView, url: String?) {
        val options = RequestOptions()
            .error(R.drawable.ic_launcher_foreground) // 에러 발생 시 표시할 이미지
            .diskCacheStrategy(DiskCacheStrategy.ALL) // 캐시 전략

        if (!url.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(url)
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        } else {
            Glide.with(view.context)
                .load(R.drawable.ic_launcher_foreground) // 기본 이미지
                .apply(options)
                .into(view)
        }
    }
}