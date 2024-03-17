package com.example.picsum.core

import android.app.Application
import android.os.Build
import android.os.StrictMode
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PicsumApp : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        setupStrictMode()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .components {
                add(RemotePhotoMapper)
            }
            .build()
    }

    private fun setupStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build(),
        )
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectActivityLeaks()
                .detectLeakedClosableObjects()
                .detectLeakedRegistrationObjects()
                .detectFileUriExposure()
                .apply {
                    if (Build.VERSION.SDK_INT >= 26) {
                        detectContentUriWithoutPermission()
                    }
                    if (Build.VERSION.SDK_INT >= 23) {
                        detectCleartextNetwork()
                    }
                    if (Build.VERSION.SDK_INT >= 29) {
                        detectCredentialProtectedWhileLocked()
                    }
                    if (Build.VERSION.SDK_INT >= 31) {
                        detectIncorrectContextUse()
                        detectUnsafeIntentLaunch()
                    }
                }
                .penaltyLog()
                .build(),
        )
    }
}
