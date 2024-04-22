package com.posite.compose2

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.DefaultLifecycleObserver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Compose2Application: Application(), DefaultLifecycleObserver {
    override fun onCreate() {
        super<Application>.onCreate()
        context = applicationContext

    }





    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context

        fun getString(@StringRes stringResId: Int): String {
            return context.getString(stringResId)
        }


    }
}