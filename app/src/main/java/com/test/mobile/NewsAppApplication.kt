package com.test.mobile

import android.annotation.SuppressLint
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.io.File

@HiltAndroidApp
class NewsAppApplication: Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: NewsAppApplication? = null

        @JvmStatic
        fun getInstance(): NewsAppApplication = instance ?: synchronized(this) {
            instance ?: NewsAppApplication().also {
                instance = it
            }
        }
    }
}