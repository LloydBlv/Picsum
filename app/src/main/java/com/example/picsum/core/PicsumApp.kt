package com.example.picsum.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PicsumApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}